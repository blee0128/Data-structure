package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author <<BENJAMIN YEN KIT LEE>>
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		//TODO: Write your code here.
		for(int i = 0; i < tOld.getLength(); i++) {
			for(int j = 0; j < tOld.getWidth(); j++) {
				//tOld.grid[i][j].census(new int[5]);
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		//TODO: Write/update your code here.
		int count = 0;
		for(int i = 0;i < town.getLength();i++) {
			for(int j = 0;j < town.getWidth();j++) {
				if(town.grid[i][j].who().equals(State.CASUAL)) {
					count += 1;
				}
			}
		}
		return count;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		//TODO: Write your code here.
		Scanner scanner = new Scanner(System.in);
		System.out.println("How to populate grid (type 1 or 2 : \n"
				+ "1: from a file \n"
				+ "2: randomly with seed");
		boolean hasNextInt = scanner.hasNextInt();
		if(hasNextInt) {
			int input = scanner.nextInt();
			scanner.nextLine();
			if(input == 1) {
				System.out.println("Enter File Name: ");
				String inputFileName = scanner.next();
//						;"src/ISP4x4.txt"
				try {
					Town town1 = new Town(inputFileName);
					double profit = 0;
					double totalProfit = 0;
					System.out.println("Start: ");
					System.out.println(town1.toString());
					profit = getProfit(town1);
					totalProfit += profit;
					for (int month = 0; month < 11; month++) {
						System.out.println(" After itr: " + Integer.toString(month+1));
						System.out.println("");
						town1 = updatePlain(town1);
						profit = getProfit(town1);
						totalProfit += profit;
						System.out.println(town1.toString());
						System.out.println("Profit:" + Integer.toString(getProfit(town1)));
						System.out.println("");
					}
					double profitPercentage = (100*totalProfit)/(town1.getLength()*town1.getWidth()*12);
					System.out.println(String.format("%.2f" , profitPercentage) + "%");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else if (input == 2) {
				System.out.println("Enter the row(integer):");
				int row = scanner.nextInt();
				System.out.println("Enter the column(integer):");
				int column = scanner.nextInt();
				System.out.println("Enter the seed(integer):");
				int seed = scanner.nextInt();
				Town town2 = new Town(row,column);
				town2.randomInit(seed);
				System.out.println(town2);
				double profit = 0;
				double totalProfit = 0;
				System.out.println("Start: ");
				System.out.println(town2.toString());
				profit = getProfit(town2);
				totalProfit += profit;
				for (int month = 0; month < 11; month++) {
					System.out.println(" After itr: " + Integer.toString(month+1));
					System.out.println("");
					town2 = updatePlain(town2);
					profit = getProfit(town2);
					totalProfit += profit;
					System.out.println(town2.toString());
					System.out.println("Profit:" + Integer.toString(getProfit(town2)));
					System.out.println("");
				}
				double profitPercentage = (100*totalProfit)/(town2.getLength()*town2.getWidth()*12);
				System.out.println(String.format("%.2f" , profitPercentage) + "%");

			} else {
				System.out.println("Invalid option");
			}
		}
	}
}

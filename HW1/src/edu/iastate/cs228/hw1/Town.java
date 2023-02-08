package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author <<BENJAMIN YEN KIT LEE>>
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		//TODO: Write your code here.
		grid = new TownCell[length][width];
		this.length = length;
		this.width = width;
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		//TODO: Write your code here.
		File  f = new File(inputFileName);
		Scanner s = new Scanner(f);
		
		String temp = s.nextLine();
//		System.out.println(temp);
		
		String[] buffer = temp.split("\\s+");
		width = Integer.parseInt(buffer[0]);
		length = Integer.parseInt(buffer[1]);
		
		
//		System.out.println(width);
//		System.out.println(length);
		
		grid = new TownCell[width][length];
		int rowIndex = 0;
		int columnIndex = 0;
		
		while(s.hasNextLine()) {
			buffer = s.nextLine().split("\\s+");
			columnIndex = 0;
			for (String character: buffer) {
				if(character.equals("C")) {
					grid[rowIndex][columnIndex] = new Casual(this,rowIndex,columnIndex);
				} else if (character.equals("R")){
					grid[rowIndex][columnIndex] = new Reseller(this,rowIndex,columnIndex);
				} else if (character.equals("O")){
					grid[rowIndex][columnIndex] = new Outage(this,rowIndex,columnIndex);
				} else if (character.equals("E")){
					grid[rowIndex][columnIndex] = new Empty(this,rowIndex,columnIndex);
				} else if(character.equals("S")){
					grid[rowIndex][columnIndex] = new Streamer(this,rowIndex,columnIndex);
				} else {
					System.out.println("Invalid File Input");
					return;
				}
				columnIndex++;
			}
			rowIndex++;
		}
		s.close();
//		for(char[] gridRow: grid) {
//			for (char gridColumn : gridRow) {
//				System.out.print(gridColumn + " ");
//			}
//			System.out.println();
//		}
	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		//TODO: Write/update your code here.
		return this.width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		//TODO: Write/update your code here.
		return this.length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		//TODO: Write your code here.
		for(int i = 0; i < this.length; i++) {
			for(int j = 0; j < this.width;j++) {
				int randomInt = rand.nextInt(5);
				if(randomInt == 0) {
					grid[i][j] = new Reseller(this,i,j);
				} else if (randomInt == 1){
					grid[i][j] = new Empty(this,i,j);
				} else if (randomInt == 2){
					grid[i][j] = new Casual(this,i,j);
				} else if (randomInt == 3){
					grid[i][j] = new Outage(this,i,j);
				} else if(randomInt == 4){
					grid[i][j] = new Streamer(this,i,j);
				} else {
					System.out.println("Invalid File Input");
					return;
				}
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		//TODO: Write your code here.
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width;j++) {
				s += grid[i][j].who().name().charAt(0) + " ";
			}
			s += "\n";
		}
		return s;
	}
}

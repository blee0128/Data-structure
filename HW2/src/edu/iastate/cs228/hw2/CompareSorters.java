package edu.iastate.cs228.hw2;

/**
 *  
 * @author<BENJAMIN YEN KIT LEE>
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;


import java.util.Scanner;

import java.io.FileNotFoundException;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		PointScanner[] scanners = new PointScanner[4]; 
		
		// assigning the algorithms a name
		Algorithm SS = Algorithm.SelectionSort;
		Algorithm IS = Algorithm.InsertionSort;
		Algorithm MS = Algorithm.MergeSort;
		Algorithm QS = Algorithm.QuickSort;
		
		//start the count at 1
		int count = 1;
		
		Scanner s = new Scanner(System.in);
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		// Select the 3 options  
		String option1 = "1 (random integers)";
		String option2 = "2 (file input)";
		String option3 = "3 (exit)";
		// Print the 3 options
		System.out.println("keys:" + "  " + option1 + " " + option2 + " " + option3);
		
		// check if there is any integer in the input, if yes, then hasNextInt will be true
		boolean hasNextInt = s.hasNextInt();
		
		while(hasNextInt) {
			System.out.print("Trial" + " " + count + ":" + " ");
			int input = s.nextInt();
			//option 1, create a point scanner object with the generateRandomPoints and algorithm
			if(input == 1) {
				System.out.print("Enter number of random points:" + " ");
				int numOfRandom = s.nextInt();
				
				Random rand = new Random();
				
				scanners[0] = new PointScanner(generateRandomPoints(numOfRandom,rand), SS);
				scanners[1] = new PointScanner(generateRandomPoints(numOfRandom,rand), IS);
				scanners[2] = new PointScanner(generateRandomPoints(numOfRandom,rand), MS);
				scanners[3] = new PointScanner(generateRandomPoints(numOfRandom,rand), QS);
			//option 2, create a point scanner object with the input file name and algorithm
			} else if(input == 2) {
				System.out.println("Points from a file");
				System.out.print("File name:" + " ");
				String inputFile = s.next();
				
				scanners[0] = new PointScanner(inputFile, SS);
				scanners[1] = new PointScanner(inputFile, IS);
				scanners[2] = new PointScanner(inputFile, MS);
				scanners[3] = new PointScanner(inputFile, QS);
			//other options will just exit
			} else {
				System.out.println("Exit");
				return;
			}
			
			// displaying the stats in the console
			System.out.println("");
			String algo = "algorithm";
			String size = "size";
			String time = "time (ns)";
			System.out.println(algo + "      " + size + "        " + time);
			System.out.println("----------------------------------------");
			
			int scanLength = scanners.length;
			for (int i = 0; i <= scanLength -1; i++) {
				scanners[i].scan();
//				System.out.println(scanners[i].toString());
//				scanners[i].writeMCPToFile();
				String p = scanners[i].stats();
				System.out.println(p);
			}
			
			System.out.println("----------------------------------------");
			
			count ++;
		}
		s.close();
		
		
//		
//		Point[] test = new Point[5];
//		test[0] = new Point(1,1);
//		test[1] = new Point(5,5);
//		test[2] = new Point(2,2);
//		test[3] = new Point(4,4);
//		test[4] = new Point(3,3);
//		AbstractSorter aSorter = new InsertionSorter(test);
//		aSorter.setComparator(0);
//		aSorter.sort();
//		
//			int x = aSorter.getMedian().getX();
//			aSorter.setComparator(1);
//			aSorter.sort();
//			int y = aSorter.getMedian().getY();
			

//			
//		PointScanner[] scanners = new PointScanner[4]; 	
//		scanners[0] = new PointScanner("points.txt",Algorithm.InsertionSort);
//		scanners[0].scan();
//		System.out.println(scanners[0].toString());
//		scanners[0].writeMCPToFile();

		
		
//		 1. create array
//		 2. print array
//		 3. sort it
//		 4. print array
//			Point[] test = new Point[5];
//			test[0] = new Point(1,1);
//			test[1] = new Point(5,1);
//			test[2] = new Point(3,1);
//			test[3] = new Point(2,1);
//			test[4] = new Point(4,1);
//			
//			for(int i = 0; i < test.length; i++) {
//				System.out.println(test[i]);
//			}
//			
//			SelectionSorter testingSort1 = new SelectionSorter(test);
//			Point[] newArray = new Point[5];
//			testingSort1.sort();
//			testingSort1.getPoints(newArray);
//			System.out.println();
//			
//			System.out.println("Selection Sort");
//			for(int i = 0; i < test.length; i++) {
//				System.out.println(newArray[i]);
//			}
//			
//			MergeSorter testingSort2 = new MergeSorter(test);
//			testingSort2.sort();
//			testingSort2.getPoints(newArray);
//			System.out.println();
//			System.out.println("Merge Sort");
//			for(int i = 0; i < test.length;i++) {
//				System.out.println(newArray[i]);
//			}
//			
//			InsertionSorter testingSort3 = new InsertionSorter(test);
//			testingSort3.sort();
//			testingSort3.getPoints(newArray);
//			System.out.println();
//			System.out.println("Insertion Sort");
//			for(int i = 0; i < test.length;i++) {
//				System.out.println(newArray[i]);
//			}
//			
//			QuickSorter testingSort4 = new QuickSorter(test);
//			testingSort4.sort();
//			testingSort4.getPoints(newArray);
//			System.out.println();
//			System.out.println("Quick Sort");
//			for(int i = 0; i < test.length;i++) {
//				System.out.println(newArray[i]);
//			}
			
//			scanners[0] = new PointScanner("points.txt",Algorithm.InsertionSort);
//			System.out.println(scanners[0].toString());

//	}
			
//		scanners[0] = new PointScanner("points.txt",Algorithm.InsertionSort);
//		System.out.println(scanners[0].toString());
//		PointScanner insertSort = scanners[0];
		
		
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
//		return null; 
		// TODO 
		Point[] storePoint = new Point[numPts];
		// throw exception if numPts < 1, else generate random points from -50 to 50
		// for x-coordinate and y-coordinate
		if (numPts < 1) {
			throw new IllegalArgumentException();
		} else {
			for(int i = 0; i < numPts;i++) {
				// generate random numbers for x-coordinate and y-coordinate
				Random generate = new Random();
				int generateRandomX = (generate.nextInt(101)- 50);
				int generateRandomY = (generate.nextInt(101)- 50);
				// create a new point object with the random x-coordinate and y-coordinate and 
				// store them in the point object
				Point p = new Point(generateRandomX,generateRandomY);
				storePoint[i] = p;
	
			}
		}
		return storePoint;
	}
	
}

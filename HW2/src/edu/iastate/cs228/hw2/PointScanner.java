package edu.iastate.cs228.hw2;

import java.io.File;
import java.util.ArrayList;
//import java.util.Collections;  


/**
 * 
 * @author 
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		// TODO
		
		int n = pts.length;
		
		// throw an exception if pts == null or pts.length == 0
		if (pts == null || n == 0) {
			throw new IllegalArgumentException();
		}
		
		points = new Point[n];
		
		for (int i = 0; i < n; i++) {
			Point q = new Point(pts[i]);
			points[i] = q;
		}
		
		// set one of the four sorting algorithm based on the parameter
		sortingAlgorithm = algo;
		
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		// TODO
		// creates an array list to store points
		ArrayList<Point> point = new ArrayList<Point>();
		
		// read a file through scanner
		File  f = new File(inputFileName);
		Scanner s = new Scanner(f);
		
		// while there are still integers, store them into points
		while(s.hasNextInt()) {
			Point storePoint;
			try {
				storePoint = new Point(s.nextInt(),s.nextInt());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new InputMismatchException();
			}
			point.add(storePoint);
		}
		
		s.close();
		
		// set one of the four sorting algorithm based on the parameter
		sortingAlgorithm = algo;
		
		// convert array list to array
		points = new Point[point.size()];
		for(int i = 0; i < points.length; i++) {
			points[i] = point.get(i);
//			System.out.println(points[i].getX());
//			System.out.println(points[i].getY());
		}
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		// TODO  
		AbstractSorter aSorter = null; 
		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
		// variable xCoordinate and yCoordinate for setting them to MCP 
		int xCoordinate = 0;
		int yCoordinate = 0;
		// variable i for the while loop
		int i = 0;
		// variable n for the number of points in the array
		int n = points.length;
		
		// assigning the algorithms a name
		Algorithm SS = Algorithm.SelectionSort;
		Algorithm IS = Algorithm.InsertionSort;
		Algorithm MS = Algorithm.MergeSort;
		Algorithm QS = Algorithm.QuickSort;
		
		// creating objects for sorting
		if (sortingAlgorithm == IS) {
			aSorter = new InsertionSorter(points);			
		} else if (sortingAlgorithm == MS) {
			aSorter = new MergeSorter(points);
		} else if (sortingAlgorithm == SS) {
			aSorter = new SelectionSorter(points);
		} else if (sortingAlgorithm == QS) {
			aSorter = new QuickSorter(points);
		} else {
			System.out.println("Wrong algorithm");
		}
		
		// start counting the time
		long begin = System.nanoTime();
		
		while (i < 2) {
			// set to sort by x-coordinate or y-coordinate
			aSorter.setComparator(i);
			
			// sort the x-coordinate and get the median after sorting
			if (i == 0) {
				aSorter.sort();
				xCoordinate = aSorter.getMedian().getX();
			}
			// sort the y-coordinate and get the median after sorting
			if (i == 1) {
				aSorter.sort();
				yCoordinate = aSorter.getMedian().getY();
			}
			
			i++;
			
		}
		
		// store the MCP to a point
		Point p = new Point(xCoordinate, yCoordinate);
		medianCoordinatePoint = p;
		
		// end counting time
		long finish = System.nanoTime();
		
		// total time to sort
		scanTime = finish - begin;
		
		Point[] newPoints = new Point[n];
		aSorter.getPoints(newPoints);
		
//		for(int i = 0; i < newPoints.length; i++) {
//			System.out.println(newPoints[i]);
//		}
	
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
//		return null; 
//		String output = format();
		// TODO 
		// display which sorting algorithm used
		String output1 = String.format("%-15s", sortingAlgorithm);
		// display the number of points used and being sorted
		String output2 = String.format("%-12d", points.length);
		// display the amount of time used
		String output3 = String.format("%-12d", scanTime);
		
		return(output1 + output2 + output3);
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		// TODO
		//prints the MCP in the format "MCP: (x, y)
		String s = "MCP:" + " " + medianCoordinatePoint.toString();
//		String s = "";
//		for(int i = 0; i < points.length; i++) {
//			s += points[i].toString() + "\n";
//		}
		
		return s;
		

	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		// TODO 
		// use try catch to avoid crashing
		try {
			// using the PrintWriter to create a new output file
			PrintWriter pw = new PrintWriter("outputFile.txt");
			// write the MCP into the output file same as the toString method above
			String s = "MCP:" + " " + medianCoordinatePoint.toString();
			pw.write(s);
			// close the file
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
		}
		
	}	

	

		
}

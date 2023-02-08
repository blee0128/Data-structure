package edu.iastate.cs228.hw2;

/**
 *  
 * @author<BENJAMIN YEN KIT LEE>
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator = null;  
	
	
	
	// Add other protected or private instance variables you may need. 
	

	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		// TODO 
		int n = pts.length;
		// throw an exception if pts == null or pts.length == 0
		if (pts == null || n == 0) {
			throw new IllegalArgumentException();
		} 
		
		points = new Point[n];
		
		// create a for loop to copy the points in array points[] to array pts[]
		for (int i = 0; i < n; i++) {
			Point q = new Point(pts[i]);
			points[i] = q;
		}
	}

		
	
	
	

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1. Assign the 
     * comparator to the variable pointComparator. 
     *  
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate
	 * 			    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		// TODO 
		// created a myComparator class at the end that overrides the compare method
		pointComparator = new myComparator();
		// throw an exception if order < 0 or order > 1
		// if order is 0, set xORy to true to compare x-coordinate
		// if order is 1, set xORy to false to compare y-coordinate
		if (order < 0 || order > 1) {
			throw new IllegalArgumentException();
		} else if (order == 0) {
			Point.xORy = true;
		} else if (order == 1){
			Point.xORy = false;
		}
		
	}

	

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	public abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		// TODO 
		// create a for loop to copy the points in array points[] to array pts[]
		for (int i = 0; i < points.length; i++) {
			Point q = new Point(points[i]);
			pts[i] = q;
		}
	}
	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		// TODO 
		Point tempPoint;
		tempPoint = points[i];
		points[i] = points[j];
		points[j] = tempPoint;
		
	}	
}
/**
 * 
 * @author <BENJAMIN YEN KIT LEE>
 * This class is a new comparator class that implements the
 * Comparator method. This class will inilitialize
 * the comparator instance to override the compare method and
 * help with the setComparator method 
 * above to assign it to the pointComparator 
 *
 */
class myComparator implements Comparator<Point> {

	@Override
	public int compare(Point o1, Point o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
	
}

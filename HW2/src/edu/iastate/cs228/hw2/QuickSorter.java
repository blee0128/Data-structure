package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author<BENJAMIN YEN KIT LEE>
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
	
	// last element index
	int n = points.length - 1;
	// first element index
	int s = 0;
	// string quickSort to be used with the algorithm
	String quickSort = "quicksort";
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		// TODO 
		super(pts);
		algorithm = quickSort;
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO 
		quickSortRec(s,n);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		// TODO

		if (first >= last) {
			return;
		}
		int seperate = partition(first, last);
		int partitionLeft = seperate - 1;
		int partitionRight = seperate + 1;

		
		quickSortRec(first, partitionLeft);
		quickSortRec(partitionRight, last);
		
		}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		// TODO 
//		return 0; 
		Point pivot = points[last];
		
		int i = first - 1;
		for (int j = first; j <= last - 1; j++) {
			if (points[j].compareTo(pivot) <= 0) {
				i++;
				swap(i, j);
			}
		}
		int k = i + 1;
		swap(k, last);
		return(i + 1);
	}	
		


	
	// Other private methods if needed ...
}

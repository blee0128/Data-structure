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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	// string mergeSort to be used with the algorithm
	String mergeSort = "mergesort";
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		// TODO  
		super(pts);
		algorithm = mergeSort;
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO 
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int arrayLength = pts.length;
		int count = 0;
		int mid = arrayLength / 2;
		int leftSize = mid;
		int rightSize = arrayLength - mid;
		
		
		if (arrayLength <= 1) {
			return;
		}
		
		Point[] leftArray = new Point[leftSize];
		Point[] rightArray = new Point[rightSize];
		
		for (int i = 0; i < leftSize; i++) {
			Point q = new Point(pts[i]);
			leftArray[i] = q;
		}


		for (int i = mid; i < arrayLength; i++) {
			Point q = new Point(pts[i]);
			rightArray[count] = q;
			count++;
		}
		
		mergeSortRec(leftArray);
		mergeSortRec(rightArray);
		
		Point[] mergePoint = new Point[pts.length];
		mergePoint = myMerge(leftArray, rightArray);
		
		
		for (int i = 0; i < mergePoint.length; i++) {
			Point q = new Point(mergePoint[i]);
			pts[i] = q;
		}
	}
	
	
	/**
	 * Merge two point arrays by iterating through both point array and appending the smallest point
	 * into the output array. After iterating through both the point array, the leftover points will 
	 * be added into the output array at the end.
	 * @param left   left point array
	 * @param right  right point array
	 */
	private Point[] myMerge(Point[] left, Point[] right) {
		int leftSubArray = left.length;
		int rightSubArray = right.length;
		int total = leftSubArray + rightSubArray;
	
		Point[] finalArray = new Point[total];
		
		int i = 0; 
		int j = 0; 
		int k = 0;
		while ((i < leftSubArray) && (j < rightSubArray)) {
			if (left[i].compareTo(right[j]) <= 0) {
				finalArray[k] = left[i];
				i++;
			} else {
				finalArray[k] = right[j];
				j++;
			}
			k++;
		}
		
//		 appending the remaining points to the output array
		while(i < leftSubArray) {
			finalArray[k] = left[i];
			i++;
			k++;
		}
		
		while(j < rightSubArray) {
			finalArray[k] = right[j];
			j++;
			k++;
		}
		
		return(finalArray);
	}

	
	// Other private methods if needed ...

}

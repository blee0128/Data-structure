package edu.iastate.cs228.hw2;

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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	// n represents the number of points
    int n = points.length;
    // string slectionSort to be used with the algorithm
    String selectionSort = "selection sort";
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		// TODO 
		super(pts);
		algorithm = selectionSort;
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO 

        for (int i = 0; i < n - 1; ++i)
        {
        	int jmin = i;
            for (int j = i+1; j < n; ++j) {
                if (points[j].compareTo(points[jmin]) < 0) {
                    jmin = j;
                }
            }
            // Swap the min element with the first element
            swap(i,jmin);
        }	
	}
}

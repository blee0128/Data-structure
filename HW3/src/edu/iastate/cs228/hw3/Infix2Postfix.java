package edu.iastate.cs228.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author <BENJAMIN YEN KIT LEE>
 * This class reads an infix expressions, converts it to postfix expression and writes it to an output file
 * 
 *
 */
public class Infix2Postfix {
	
	String[] postfix;
	ArrayList<String[]> input;
	String output = "";
	int count = 0;
	
	
	/**
	 * This method reads the input file and store the contents of the input file in an
	 * ArrayList called input for every line of infix expression. This method will
	 * check if there is a next line and uses the hasNextLine() and using the count
	 * variable to keep track of how many lines of infix expression there are in the 
	 * input file. Every token will be split using the split method and adding them
	 * into the arrayList.
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public void readInput(String filename) throws IOException {
	    File f = new File (filename);
	    Scanner s = new Scanner(f);
	    
	    while(s.hasNextLine()) {
	    	s.nextLine();
	    	this.count++;
	    }
	    s.close();
	    
	    s = new Scanner(f);
	    this.input = new ArrayList<String[]>();
	    
	    while(s.hasNextLine()) {
	    	String temp = s.nextLine();
	    	String[] buffer = temp.split("\\s+");

	    	this.input.add(buffer);
			
		}

		s.close();

	}
	
	/**
	 * This method checks the precedence based on the infix to Postfix requirements.
	 * ^ > * == % == / > + == -
	 * we will provide an operand with a 0 value. + and - will get a 1 value. *, % and / will 
	 * get a 2 value. ^ will get a value of 3. 
	 * By using what is shown above, we are able to control what is push onto the stack
	 * and what is pop from the stack. 
	 * 
	 * @param c
	 * @return
	 */
	public static int preced(String x){
        if(x.equals("+")) {
        	return 1;
        } else if (x.equals("-") || x.equals("–")) {
        	return 1;
        } else if (x.equals("*")) {
        	return 2;
        } else if (x.equals("%")) {
        	return 2;
        } else if (x.equals("/")) {
        	return 2;
		}else if (x.equals("^")) {
        	return 3;
		} else {
			return 0;
		}
	}
	
	/**
	 * This method accepts an index indexing the arrayList for which infix expression
	 * we are going to convert from infix to postfix. It follows the infix to postfix rules
	 * and push and pop accordingly from the stack. When it sees a (),it doesn't add them into
	 * the outputArray, which is what is required in the postfix. There is also an output variable
	 * to store the postfix information.
	 * 
	 * @param index
	 */
	public void InfixConvertToPostfix(int index) {
		Stack<String> stk = new Stack<String>();
		String[] outputArray = this.input.get(index);
        postfix = new String[outputArray.length];
        output = "";
        int countParenthesis = 0;
        int insideParenthesis = 0;
        int operand = 0;
        int operator = 0;
        
        // for this part, we will loop through the output array to see if the element is an operand
        // or an operator.
        for(int i = 0 ; i < outputArray.length ; i++) {
        	String x = outputArray[i];
        	
        	// if it is an opening parenthesis, we will push in into the stack and count the parenthesis
        	// at the same time we are also counting the element inside the parenthesis with variable insideparenthesis
    		// to see if there any subexpression in the parenthesis. 
        	if (x.equals("(")) {
        		countParenthesis++;
        		stk.push(x);
        		insideParenthesis = 0;
        	} 
        	
        	// if it is a closing parenthesis, we will first check if the parenthesis count is less than 0
        	// if it is less than 0, meaning there are no opening parenthesis detected
    		// if it is greater than 0, we will pop whatever is in the stack till we see the opening
    		// parenthesis
        	else if (x.equals(")")) {
        		if(countParenthesis < 1) {
        			output = "Error: no opening parenthesis detected";
        			return;
        		} else {

	        		String y = stk.pop();
	        		
					while(!y.equals("(")) {
	        			output += (y + " ");
	        			y = stk.pop();
	        			insideParenthesis ++;
	        		}
					// if insideParenthesis == 0, meaning there is a free-floating pair of parenthesis
					// in the expression, we print an error
	        		if(insideParenthesis == 0) {
	        			output = "Error: no subexpression detected ()";
	        			return;
	        		}
	        		
	        		
	        		countParenthesis--;
        		}
        	} 
        	
        	// if preced(x) > 0, meaning it is an operator, we will need to push or pop it depending
        	// on its precedence rules. We also keep track of the operator count to see if there are 
        	// correct numbers of operator and operands in the expression
        	else if(preced(x) > 0) {
        		while(stk.isEmpty()!= true && preced(stk.peek()) >= preced(x)) {
        			if(stk.peek().equals("^") && x.equals("^")) {
        				break;
        			}
        			
        			output += (stk.pop() + " ");
        		}
        		stk.push(x);
        		operator++;
        		//checking if there are too many operators
        		if((operand < operator) || (operand <= operator && outputArray.length - 1 == i)) {
                 	output = "Error: too many operators (" + x + ")";
         			return;
        		}
        	}
        	
        	// if we come to the end of the loop, it means that it is an operand, we will keep track of the count
    		// of operands in the expression and check if there are correct number of operands with operator
    		// if there are too many operands, we will print an error
        	else{
                  output += (x + " ");
                  insideParenthesis++;
                  operand++;
                  if(operand > operator + 1) {
                  	output = "Error: too many operands (" + x + ")";
          			return;
          		  }
        	}
        }
        
        int size = stk.size();
        // after the loop we will reach here and pop the remaining elements in the stack
    	for (int i = 0; i < size; i++) {
    		if(stk.size() == 1) {
        		output += stk.pop();
        		break;
        	}
    		if(stk.size() == 0) {
        		break;
        	}
    		output += (stk.pop() + " ");
    	}
    	
    	
    	// we check if the count of Parenthesis is greater than 0, if it is after scanning through the entire
    	// expression, it means that there are more opening parenthesis compared to closing parenthesis
    	// we will print an error for this
    	if(countParenthesis > 0) {
			output = "Error: no closing parenthesis detected";
			return;
    	}
	}
    	

	
	public static void main(String[] args) throws IOException {
		Infix2Postfix infix = new Infix2Postfix();
		infix.readInput("input.txt");
		//use the PrintWritter method to create a file name output.txt and print the results into the output file
//		PrintWriter pw = new PrintWriter("output.txt");
//		for(int i = 0; i < infix.count;i++) {
//			infix.InfixConvertToPostfix(i);
//			pw.println(infix.output.trim());
//		}
//		pw.close();
		infix.InfixConvertToPostfix(16);
		System.out.println(infix.output);
	
	}
}


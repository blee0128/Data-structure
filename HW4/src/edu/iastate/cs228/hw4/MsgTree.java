package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* 
* @author <BENJAMIN YEN KIT LEE>
* 
* This class unzip a message with a binary-tree-based algorithm and decode the message 
* in the file
*
*/

public class MsgTree {
	public char payloadChar; 
	public MsgTree left; 
	public MsgTree right;
	
	
	private String encoding;
	private String binary;
	private char[] decode;
	private char[] encode;
	int count = 0;
	private static MsgTree root;
	private int countBits;
	private int countcharacters;
	
	
	/**
	 * This method reads the input file and store the contents of the input file into the 
	 * variable encoding and binary. There might be three lines and two lines in the file.
	 * If there are two lines, the first line will be the encoding and the second line will
	 * be the binary. If there are three lines, the first two line will be the encoding and
	 * the second line will be the binary. If three lines, we account for a new line character
	 * called next line.
	 * 
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
//		    System.out.println(this.count);
		    s.close();
		    s = new Scanner(f);
		    
		    if(this.count == 2) {
		    	encoding = s.nextLine();
//		    	System.out.println(encoding);
		    }
		    if(this.count == 3) {
		    	String temp = s.nextLine();
		    	String temp2 = s.nextLine();
		    	encoding = temp + "\n" + temp2;
//		    	System.out.println(encoding);
		    }
		    
		    binary = s.nextLine();
//		    chBinary = binary.toCharArray();
		    
//		    System.out.println(binary);
//		    for(int i = 0; i <chBinary.length;i++) {
//		    	System.out.println("char at " + i + " is " + chBinary[i]);
//		    }
		    s.close();		

	}
	
	/*static char idx to keep track of the location in the tree string for recursive solution*/
	private static int staticCharIdx = 0;
	
	/**
	 * This method is a recursive call that builds a binary tree in preorder traversal using
	 * the staticCharIdx to keep track of where the location we are in the binary tree. That 
	 * way we can pass the same tree string using a recursive call and updating the staticCharIdx. 
	 * The first if statement is a base case that tracks if we reach the end of the string. If
	 * we see a '^', we are in an internal node. and we go left if there is an available slots.
	 * If we reach a character other than '^', this means we reach the leaf. If we are not able
	 * to go left, we will go right in the preorder traversal.
	 * 
	 * @param encodingString
	 */
	public MsgTree(String encodingString) {
		
		if(staticCharIdx >= encodingString.length()) {
			return;
		}
		
		this.payloadChar = encodingString.charAt(staticCharIdx);
		
		staticCharIdx++;
		
		if(this.payloadChar != '^') {
			return;
		}
		
		left = new MsgTree(encodingString);
		right = new MsgTree(encodingString);
		
	}
	
	/**
	 * 
	 * This method creates a node that takes a char character, meaning this node is a leaf node.
	 * The left and right will be null in this case.
	 * 
	 * 
	 * @param payloadChar
	 */
	public MsgTree(char payloadChar){
		this.payloadChar = payloadChar;
	}
	
	/**
	 * 
	 * This method prints each character and the code for each character. This is a recursive call
	 * that goes left till we can't go left and only go right in a preorder traversal. We reach a 
	 * leaf when the left and right child of the parent is null. When we reach a leaf, there are
	 * three choices, if the char is '\n', it means the next line, if the char is ' ', it means a
	 * space, or else it will be a character. If the left is not null, we keep going left till we
	 * reach a leaf. After that we will go back to the parent and go right. If right is not null, 
	 * it will go right and go left in preorder traversal.
	 * 
	 * 
	 * 
	 * @param root
	 * @param code
	 */
	public static void printCodes(MsgTree root, String code){
		
		if(root.left == null && root.right == null) {
			if(root.payloadChar == '\n') {
				System.out.println("next line" + "              " + code);
				return;
			} else if(root.payloadChar == ' ') {
				System.out.println("  space" + "                " + code);
				return;
			} else {
				System.out.println("    " + root.payloadChar + "                  " + code);
				return;
			}
		}
		
		if(root.left != null) { 
			code += "0";
			printCodes(root.left,code);
			code = code.substring(0, code.length() - 1);
		}
//		
		if(root.right != null) {
			code += "1";
			printCodes(root.right,code);
			code = code.substring(0, code.length() - 1);
		}
		
		
	}
	
	
	
	/**
	 * 
	 * Given an input string, we will first store them into a char array. To decode, we will
	 * set the staticCharIdx back to 0 and iterate throught the message. The first if statement
	 * in the while loop checks if we reach the end of the string and pringts the character.
	 * We then increment the countcharacters to use it in the statistics in the extra credit.
	 * If we reach a leaf where left and right is null, we print the character and set the leaf
	 * back to its root and traverse again. We created a static root so that every time we reach
	 * a leaf, we are able to go back to its original root. If the char is 0, it will go left and
	 * increase the staticCharIdx. If the char is 1, it will go right and increase the staticCharIdx.
	 * 
	 * @param codes
	 * @param msg
	 */
	public void decode(MsgTree codes, String msg) {
		
		decode = msg.toCharArray();
		countBits = decode.length;
		root = codes;
		while(staticCharIdx <= msg.length()) {
			if(staticCharIdx == msg.length()) {
				System.out.print(codes.payloadChar);
				countcharacters++;
				return;
			}
			if(codes.left == null && codes.right == null) {
				System.out.print(codes.payloadChar);
				countcharacters++;
				codes = root;
			}
			if(decode[staticCharIdx] == '0') {
				codes = codes.left;
				staticCharIdx++;
			} else {
				codes = codes.right;
				staticCharIdx++;
			}
		}
	}
	/**
	 * This is a default constructor that does not take anything.
	 * It is to create a new object in the main method to read the input file.
	 */
	public MsgTree() {
		
	}
	
	public static void main(String[] args) throws IOException {
		// create a new object using the default constructor to read a file
		MsgTree mt = new MsgTree();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter filename to decode: ");
		boolean hasNext = scanner.hasNext();
		String temp = "";
		if(hasNext) {
			temp = scanner.next();
		}
		mt.readInput(temp);
		
		
		// create a variable encodingString that has the eoncoding line to use it
		// to build the binary tree
		String encodingString = mt.encoding;
		// the decodingString is a variable used in the decode method below
		String decodingString = mt.binary;
		MsgTree mtTree = new MsgTree(encodingString);
		
		
		// reset the staticCharIdx to use it in the decode method
		staticCharIdx = 0;
		
		// apply the printCodes() mehod that recursively call the preorder traversal of the
		// MsgTree and prints all the characters and their bit codes
		System.out.println("character           code");
		System.out.println("------------------------------");
		String printCode = "";
		mt.printCodes(mtTree, printCode);
		System.out.println();
		
		
		//print the message after decoding using the decode method
		System.out.println("MESSAGE:");
		mt.decode(mtTree, decodingString);
		System.out.println();
		System.out.println();
		
		
		// extra credit to print the statistics 
		System.out.println("STATISTICS:");
		System.out.print("Avg bits/char:               ");
		double avg = mt.countBits * 1.0 / mt.countcharacters;
		System.out.println(avg);
		System.out.print("Total characters:            ");
		System.out.println(mt.countcharacters);
		System.out.print("Space savings:               ");
		double spaceSaving = (1 - (avg / 16))*100;
		System.out.println(spaceSaving + "%");

	}
	
	
	
}

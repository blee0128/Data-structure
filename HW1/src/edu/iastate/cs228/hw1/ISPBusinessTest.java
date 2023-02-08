package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class ISPBusinessTest {

	@Test
	void test() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		int exp = 1;
		assertEquals(ISPBusiness.getProfit(t), exp);
	}
	
	void test1() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		String exp = "E E E E \nC C O E \nC O E O \nC E E E \n";
		assertEquals(ISPBusiness.updatePlain(t), exp);
	}

}

package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TownTest {

	@Test
	void test() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		String exp = "O R O R \nE E C O \nE S O S \nE O R R \n";
				
		assertEquals(t.toString(), exp);
	}

}

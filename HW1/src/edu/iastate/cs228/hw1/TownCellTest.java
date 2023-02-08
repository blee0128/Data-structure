package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TownCellTest {

	@Test
	void test() {
		
		int RESELLER = 0;
		int EMPTY = 1;
		int CASUAL = 2;
		int OUTAGE = 3;
		int STREAMER = 4;
		
		Town t = new Town(3,3);
		t.grid[0][0] = new Reseller(t,0,0);
		t.grid[0][1] = new Reseller(t,0,1);
		t.grid[0][2] = new Reseller(t,0,2);
		t.grid[1][0] = new Casual(t,1,0);
		t.grid[1][1] = new Casual(t,1,1);
		t.grid[1][2] = new Casual(t,1,2);
		t.grid[2][0] = new Empty(t,0,0);
		t.grid[2][1] = new Empty(t,1,1);
		t.grid[2][2] = new Empty(t,2,2);

		
		TownCell tc = t.grid[1][1];
		int[] myArray = new int[5];
		tc.census(myArray);
		int resellerNumber = 3;
		int casualNumber = 2;
		int emptyNumber = 3;
		int outageNumber = 0;
		int streamerNumber = 0;
				
		assertEquals(resellerNumber, myArray[RESELLER], "RESELLER");
		assertEquals(casualNumber, myArray[CASUAL], "CASUAL");
		assertEquals(emptyNumber, myArray[EMPTY], "EMPTY");
		assertEquals(outageNumber, myArray[OUTAGE], "OUTAGE");
		assertEquals(streamerNumber, myArray[STREAMER], "STREAMER");
	}

}

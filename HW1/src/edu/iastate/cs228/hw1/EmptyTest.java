package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptyTest {

	@Test
	void test() {
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
		
		assertEquals(t.grid[2][1].who(),State.EMPTY);
	}

}

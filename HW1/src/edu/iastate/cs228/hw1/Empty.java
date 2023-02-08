package edu.iastate.cs228.hw1;

public class Empty extends TownCell{
	
	public Empty(Town p, int r, int c) {
		super(p,r,c);
	}

	@Override
	public void census(int[] nCensus) {
		// TODO Auto-generated method stub
		super.census(nCensus);
	}

	@Override
	public State who() {
		// TODO Auto-generated method stub
		return State.EMPTY;
	}

	@Override
	public TownCell next(Town tNew) {
		// TODO Auto-generated method stub
		int[] array = new int[5];
		census(array);
		if(array[EMPTY] + array[OUTAGE] <= 1) {
			return new Reseller(tNew, this.row, this.col);
		} else {
			return new Casual(tNew, this.row, this.col);
		}
	}
}

package edu.iastate.cs228.hw1;

public class Outage extends TownCell{
	
	public Outage(Town p, int r, int c) {
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
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		// TODO Auto-generated method stub
		return new Empty(tNew, this.row, this.col);
	}
}

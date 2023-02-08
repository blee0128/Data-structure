package edu.iastate.cs228.hw1;

public class Reseller extends TownCell{
	
	public Reseller(Town p, int r, int c) {
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
		return State.RESELLER;
	}

	@Override
	public TownCell next(Town tNew) {
		// TODO Auto-generated method stub
		int[] array = new int[5];
		census(array);
		if(array[CASUAL] <= 3) {
			return new Empty(tNew, this.row, this.col);
		} else if(array[EMPTY] >= 3) {
			return new Empty(tNew, this.row, this.col);
		} else if (array[CASUAL] >= 5) {
			return new Streamer(tNew, this.row, this.col);
		} else {
		return new Reseller(tNew, this.row, this.col);
		}
	}
}

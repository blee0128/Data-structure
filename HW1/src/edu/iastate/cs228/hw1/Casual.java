package edu.iastate.cs228.hw1;

public class Casual extends TownCell{
	
	public Casual(Town p, int r, int c) {
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
		return State.CASUAL;
	}

	@Override
	public TownCell next(Town tNew) {
		int[] array = new int[5];
		census(array);
		if (array[EMPTY] + array[OUTAGE] <= 1){
			return new Reseller(tNew, this.row, this.col);
		} else if(array[RESELLER] > 0) {
			return new Outage(tNew, this.row, this.col);
		} else if(array[STREAMER] > 0) {
			return new Streamer(tNew, this.row, this.col);
		} else if (array[CASUAL] >= 5) {
			return new Streamer(tNew, this.row, this.col);
		} else {
		return new Casual(tNew, this.row, this.col);
		}
	}

}

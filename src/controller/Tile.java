package controller;

import java.util.Random;

import helpers.Coordinate;

public class Tile {

	private boolean mine;
	private boolean flagged;
	private final static float probabilty = 0.9f;
	private Coordinate coord;
	protected int numNextTo;
	
	public Tile(int x , int y){
		coord = new Coordinate(x, y);
		Random rand = new Random();
		if (rand.nextFloat() > probabilty){
			mine = true;
		} else {
			mine = false;
		}
	}
	
	
	public int getNumNextTo() {
		return numNextTo;
	}
	
	public boolean isMine(){
		return mine;
	}
		
	public boolean hasFlag(){
		return flagged;
	}
	
	public void addFlag(){
		flagged = true;
	}
	
	public void removeFlag(){
		flagged = false;
	}
	
	public Coordinate getCoord(){
		return coord;
	}
	
	@Override
	public String toString(){
		return coord.toString();
	}
}

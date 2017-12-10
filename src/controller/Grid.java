package controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

	public int gridSizex = 20;
	public int gridSizey = 20;
	private Tile[][] tiles;
	
	public static void main (String[] args){
		Grid grid = new Grid();
		grid.click(19, 19);
	}

	public Grid() {
		tiles = new Tile[gridSizex][gridSizey];
		for (int y = 0; y < gridSizey; y++) {
			for (int x = 0; x < gridSizex; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}
	}

	public void addFlag(int x, int y) {
		tiles[x][y].addFlag();
	}
	
	public void removeFlag(int x, int y) {
		tiles[x][y].removeFlag();
	}
	
	public boolean isFlag(int x, int y) {
		return tiles[x][y].hasFlag();
	}

	public void click(int x, int y) {
		tiles[x][y].numNextTo=0;
		unCoverAll(x, y);
	}

	public boolean clickMine(int x, int y) {
		return tiles[x][y].isMine();
	}

	public void unCoverAll(int x, int y) {
		ArrayList<Tile> fringe = new ArrayList<Tile>();
		ArrayList<Tile> visited = new ArrayList<Tile>();

		fringe.add(tiles[x][y]);
		visited.add(tiles[x][y]);
		int i = 0;

		while (!(fringe.isEmpty())) {
			if ((i = (mineNextTo(fringe.get(0)))) != 0) {
				fringe.get(0).numNextTo = i;
				visited.add(fringe.get(0));
				fringe.remove(0);
			} else {
				fringe.get(0).numNextTo=-1;
				fringe.addAll(getNextTo(fringe.get(0), visited));
				visited.addAll(getNextTo(fringe.get(0), visited));
				fringe.remove(0);
				//System.out.println(Arrays.toString(fringe.toArray()));
			}
		}
	}

	public ArrayList<Tile> getNextTo(Tile tile, ArrayList<Tile> visited) {
		int x = tile.getCoord().getx();
		int y = tile.getCoord().gety();
		ArrayList<Tile> returner = new ArrayList<Tile>();
		
		if (((x + 1) < gridSizex) && (!visited.contains(tiles[x+1][y]))) {
			returner.add(tiles[x + 1][y]);
		}
		if (((y + 1) < gridSizey) && (!visited.contains(tiles[x][y+1]))) {
			returner.add(tiles[x][y + 1]);
		}
		if (((x - 1) >= 0) && (!(visited.contains(tiles[x-1][y])))) {
			returner.add(tiles[x - 1][y]);
		}
		if (((y - 1) >= 0) && (!visited.contains(tiles[x][y-1]))) {
			returner.add(tiles[x][y - 1]);
		}
		

		return returner;
	}

	public int mineNextTo(Tile tile) {

		int x = tile.getCoord().getx();
		int y = tile.getCoord().gety();
		int numMines = 0;

		if (((y + 1) < gridSizey) && (tiles[x][y + 1].isMine())) {
			numMines++;
		}
		if (((x + 1) < gridSizex) && ((y + 1) < gridSizey) && (tiles[x + 1][y + 1].isMine())) {
			numMines++;
		}
		if (((x + 1) < gridSizex) && (tiles[x + 1][y].isMine())) {
			numMines++;
		}
		if (((x + 1) < gridSizex) && ((y - 1) >= 0) && (tiles[x + 1][y - 1].isMine())) {
			numMines++;
		}
		if (((y - 1) >= 0) && (tiles[x][y - 1].isMine())) {
			numMines++;
		}
		if (((x - 1) >= 0) && ((y - 1) >= 0) && (tiles[x - 1][y - 1].isMine())) {
			numMines++;
		}
		if (((x - 1) >= 0) && (tiles[x - 1][y].isMine())) {
			numMines++;
		}
		if (((x - 1) >= 0) && ((y + 1) < gridSizey) && (tiles[x - 1][y + 1].isMine())) {
			numMines++;
		}
		return numMines;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}

}

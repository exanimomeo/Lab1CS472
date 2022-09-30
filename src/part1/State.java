package part1;

import java.util.List;
import java.util.ArrayList;

public class State {
	int[] matrix;
	int[] initialstate;
	State[] parallelstates;
	
	public static State goalState() {
		int[] goal = new int[]{1,2,3,4,5,6,7,8,0};
		return new State(goal);
	}
	
	public State(int[] input) {
		matrix = input;
		initialstate = input;
	}
	
	public State(int[] input, int[] startinginput) {
		matrix = input;
		initialstate = startinginput;
	}
	
	public int getVal(int i) { return matrix[i]; }
	
	public List<Direction> getPossibleMoves(){
		List<Direction> possibleMoves = new ArrayList<Direction>();
		int hor = -1;
		int ver = -1;
		for (int i = 0; i < 9; i++) {
			if (getVal(i) == 0) {
				hor = i % 3;
				ver = i / 3;
			}
		}
		if (ver > 0) {
			possibleMoves.add(Direction.UP);
		}
		if (ver < 2) {
			possibleMoves.add(Direction.DOWN);
		}
		if (hor < 2) {
			possibleMoves.add(Direction.RIGHT);
		}
		if (hor > 0) {
			possibleMoves.add(Direction.LEFT);
		}
		return possibleMoves;
	}
	
	public State move(Direction d) {
		int[] newMatrix = new int[9];
		for (int i = 0; i < 9; i++) {
			newMatrix[i] = getVal(i);
		}
		int hor = -1;
		int ver = -1;
		for (int i = 0; i < 9; i++) {
			if (getVal(i) == 0) {
				hor = i % 3;
				ver = i / 3;
				break;
			}
		}
		//eightpuzzle.numNodes++;
		switch (d) {
		case UP:
			if (ver != 0) {
				newMatrix[hor + (ver - 1) * 3] = 0;
				newMatrix[hor + ver * 3] = getVal(hor + (ver - 1) * 3);
			}
			return new State(newMatrix, initialstate);
		case DOWN:
			if (ver != 2) {
				newMatrix[hor + (ver + 1) * 3] = 0;
				newMatrix[hor + ver * 3] = getVal(hor + (ver + 1) * 3);
			}
			return new State(newMatrix, initialstate);
		case RIGHT:
			if (hor != 2) {
				newMatrix[(hor + 1) + ver * 3] = 0;
				newMatrix[hor + ver * 3] = getVal((hor + 1) + ver * 3);
			}
			return new State(newMatrix, initialstate);
		case LEFT:
			if (hor != 0) {
				newMatrix[(hor - 1) + ver * 3] = 0;
				newMatrix[hor + ver * 3] = getVal((hor - 1) + ver * 3);
			}
			return new State(newMatrix, initialstate);
		default:
			return new State(newMatrix);
		}
	}
	
	public int compareTo(State state) {
		for (int i = 0; i < 9; i++) {
			if (state.getVal(i) != getVal(i)) {
				return 1;
			}
		}
		return 0;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int j = 0; j < 3; j++) {
			s += "[";
			for (int i = 0; i < 3; i++) {
				s += matrix[i + j*3];
				if (i != 2) {
					s += ",";
				}
			}
			s += "]\n";
		}
		return s;
	}
	
	public int getInvCount()
	{
	    int inversionCount = 0;
	    for (int i = 0; i < 9; i++)
	        for (int j = i + 1; j < 9; j++)
	            if (getVal(i) > 0 &&
	                            getVal(j) > 0 && getVal(i) > getVal(j))
	                inversionCount++;
	    return inversionCount;
	}
	 
}

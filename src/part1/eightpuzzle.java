package part1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import java.util.function.ToDoubleFunction;
import java.time.*;
import bookcode.*;
import bookcode.framework.qsearch.QueueSearch;
import bookcode.framework.qsearch.TreeSearch;
import bookcode.framework.*;
import bookcode.framework.qsearch.GraphSearch;


public class eightpuzzle {
	public static long maxTime;
	public static int numNodes;
	public static int depth;
	public static String sequence;
	public static long startTime;
	public static long totalTime;
	
	public static void main(String[] args) throws IOException {
		//Creates a scanner that will read from the name of the file
		File f = new File(args[0]);
		Scanner s = new Scanner(f);
		
		String a = args[1];
		int algo = 0;
		if(a.compareTo("BFS")==0) {
			algo = 0;
		} else if (a.compareTo("IDS") == 0) {
			algo = 1;
		} else if (a.compareTo("h1") == 0) {
			algo = 2;
		} else if (a.compareTo("h2") == 0) {
			algo = 3;
		} else if (a.compareTo("h3") == 0) {
			algo = 4;
		}
		int[] matrix = new int[9];
		//note: matrix is a 9 long array, but it will be interpreted
		//as three rows of three. Also note: 0 is the number for empty
		
		//fill array.
		for (int i = 0; i < 9; i++) {
			String n = s.next();
			if (n.compareTo("_") != 0) {
				matrix[i] = Character.getNumericValue(n.charAt(0));
			}
		}
		s.close();
		startTime = System.currentTimeMillis();
		maxTime = 15*60*1000 + startTime;
		
		//createOutput("140173.txt","140173.txt","h2", 2576, "ULLDRRULLDRDLURRDLLURRULL",3827,25);
		if (isSolvable(new State(matrix))){
			runAlgorithm(matrix,algo);
			System.out.println(sequence);
			System.out.println("Time: " + (totalTime / 1000) + " sec " + (totalTime % 1000) + " millisec");
			System.out.println("Num nodes: " + numNodes);
			System.out.println("Depth: " + depth);
			
		} else {
			System.out.println("Not Solvable");
			System.out.println("Inv Count: " + (new State(matrix)).getInvCount());
		}
		
	}
	
	public static void runAlgorithm(int[] matrix,int algo) {
		State state = new State(matrix);
		switch(algo) {
			case 0:
				BFS(state);
				break;
			case 1:
				IDS(state);
				break;
			case 2:
				h1(state);
				break;
			case 3:
				h2(state);
				break;
			case 4:
				h3(state);
				break;
		}
	}
	
	public static void BFS(State state) {
		EightPuzzleProblem epp = new EightPuzzleProblem(state);
		BreadthFirstSearch<State, Direction> bfs = new BreadthFirstSearch();
		String s = convertListToString(bfs.findActions(epp).get());
		totalTime = System.currentTimeMillis() - startTime;
		sequence = s;
		depth = s.length();
		//DONE
	}
	
	public static void IDS(State state) {
		EightPuzzleProblem epp = new EightPuzzleProblem(state);
		IterativeDeepeningSearch<State,Direction> ids = new IterativeDeepeningSearch();
		String s = convertListToString(ids.findActions(epp).get());
		totalTime = System.currentTimeMillis() - startTime;
		sequence = s;
		depth = s.length();
		//DONE MAYBE REVISE NODE COUNTS
	}
	
	public static void h1(State state) {
		EightPuzzleProblem epp = new EightPuzzleProblem(state);
		GraphSearch<State,Direction> gs = new GraphSearch();
		MisplacedEvaluationFunction mef = new MisplacedEvaluationFunction();
		AStarSearch<State,Direction> ass = new AStarSearch<State, Direction>(gs, mef);
		String s = convertListToString(ass.findActions(epp).get());
		totalTime = System.currentTimeMillis() - startTime;
		sequence = s;
		depth = s.length();
		//DONE
	}
	
	public static void h2(State state) {
		EightPuzzleProblem epp = new EightPuzzleProblem(state);
		TreeSearch<State,Direction> ts = new TreeSearch();
		ManhattenEvaluationFunction mef = new ManhattenEvaluationFunction();
		AStarSearch<State,Direction> ass = new AStarSearch<State, Direction>(ts, mef);
		String s = convertListToString(ass.findActions(epp).get());
		totalTime = System.currentTimeMillis() - startTime;
		sequence = s;
		depth = s.length();
		//DONE
		//Runs out of memory for any normal sized problem
	}
	
	public static void h3(State state) {
		
	}
	
	public static boolean isSolvable(State state) {
		return state.getInvCount()%2 == 0;
	}
	
	public static void createOutput(String name, String algo, long totalTime, String path, int totalNodes, int length) throws IOException {
		File f = new File(name + " report" + algo + ".txt");
		if (f.createNewFile()) {
			FileWriter w = new FileWriter(f);
			String s = "";
			s += "Java eightpuzzle.java f:" + name + " alg:" + algo + "\n";
			s += "Total nodes generated: " + totalNodes + "\n";
			s += "Total time taken: " + ((int) totalTime / 1000) + " sec " + ((int) totalTime%1000) + " microSec\n";
			s += "Path length: " + length + "\n";
			s += "Path: " + path;
			w.write(s);
			w.close();
		}
		
	}
	
	public static String convertListToString(List<Direction> directions) {
		String s = "";
		for (Direction d : directions) {
			switch(d) {
			case UP:
				s += "U";
				break;
			case DOWN:
				s += "D";
				break;
			case RIGHT:
				s += "R";
				break;
			case LEFT:
				s += "L";
				break;
			}
		}
		return s;
	}
	
}

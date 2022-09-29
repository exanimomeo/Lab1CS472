package part1;

import bookcode.EvaluationFunction;
import bookcode.framework.Node;

public class MisplacedEvaluationFunction extends EvaluationFunction<State,Direction>{

	@Override
	public double applyAsDouble(Node<State, Direction> value) {
		double misplacement = 0.;
		for(int i = 0; i < 9; i++) {
			misplacement += valDistFromDesired(i%3,i/3,value.getState().getVal(i)) == 0 ? 0 : 1;
		}
		return misplacement;
	}
	
	private static double valDistFromDesired(int hor, int ver, int num) {
		double distance = 0;
		distance += Math.abs(hor - ((int) num%3)); //adds the number's horizontal distance from desired
		distance += Math.abs(ver - ((int) num/3)); //adds the number's vertical distance from desired
		return 0.f;
	}
}

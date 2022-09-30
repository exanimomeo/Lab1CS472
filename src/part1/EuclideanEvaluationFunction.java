package part1;

import bookcode.EvaluationFunction;
import bookcode.framework.Node;
public class EuclideanEvaluationFunction extends EvaluationFunction<State,Direction>{

	@Override
	public double applyAsDouble(Node<State, Direction> value) {
		double distance = 0.;
		for(int i = 0; i < 9; i++) {
			distance += valDistFromDesired(i%3,i/3,value.getState().getVal(i));
		}
		return distance;
	}
	
	private static double valDistFromDesired(int hor, int ver, int num) {
		double distance = 0;
		double val1 = hor - ((int) num%3);
		double val2 = ver - ((int) num/3);
		distance += Math.sqrt(val1 * val1 + val2 * val2);
		return distance;
	}
}

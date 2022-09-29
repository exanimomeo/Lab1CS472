package part1;
import java.util.List;

import bookcode.framework.problem.Problem;

public class EightPuzzleProblem implements Problem<State, Direction> {
	State initialState;
	
	public EightPuzzleProblem(State initialState) {
		this.initialState = initialState;
	}
	
	@Override
	public State getInitialState() {
		return initialState;
	}

	@Override
	public List<Direction> getActions(State state) {
		return state.getPossibleMoves();
	}

	@Override
	public State getResult(State state, Direction action) {
		return state.move(action);
	}

	@Override
	public boolean testGoal(State state) {
		if (state.compareTo(State.goalState()) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public double getStepCosts(State state, Direction action, State stateDelta) {
		return 1;
	}
	
}

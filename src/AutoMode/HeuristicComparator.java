package AutoMode;

import java.util.Comparator;

public class HeuristicComparator implements Comparator<PuzzleState>{

	@Override
	public int compare(PuzzleState state1, PuzzleState state2) {
		float state1Heuristic = state1.costHeursiticTotal();
		float state2Heuristic = state2.costHeursiticTotal();
		if (state1Heuristic < state2Heuristic) return -1;
		if (state1Heuristic > state2Heuristic) return 1;
		return 0;
	}

}

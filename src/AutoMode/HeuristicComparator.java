package AutoMode;

import java.util.Comparator;

public class HeuristicComparator implements Comparator<PuzzleState>{

	@Override
	public int compare(PuzzleState state1, PuzzleState state2) {
		return state1.getHeuristicValue() - state2.getHeuristicValue();
	}

}

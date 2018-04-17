package AutoMode;

import java.util.Comparator;

public class HeuristicComparator implements Comparator<PuzzleState>{

	@Override
	public int compare(PuzzleState state1, PuzzleState state2) {
		float num1=state1.costHeursiticTotal(), num2=state2.costHeursiticTotal();
		return Float.compare(num1, num2);

	}

}

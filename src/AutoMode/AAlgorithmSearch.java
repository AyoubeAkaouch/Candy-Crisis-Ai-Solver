package AutoMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AAlgorithmSearch {

	public AAlgorithmSearch() {
	}
	/*
	 * Steps: 1: Verify legal moves based on blank position 2: Generate State
	 * object with puzzle position and heuristic value. 3: Remove states that
	 * are in the closed queue 4: Put all valid states in the open queue 5:
	 * Select best heuristic value state from open queue and remove it from
	 * queue 6: Switch state, add in closed queue
	 */

	public List<PuzzleState> Start(ArrayList<char[]> puzzles) {
		List<PuzzleState> listSolvedStates = new ArrayList<PuzzleState>();
		for (char[] puzzle : puzzles) {
			float heuristicValue = CalculateHeuristic(puzzle);
			HeuristicComparator comparator = new HeuristicComparator();
			PuzzleState state;

			System.out.println("Solving puzzle");
			Queue<PuzzleState> openState = new PriorityQueue<PuzzleState>(comparator);
			List<PuzzleState> closedState = new ArrayList<PuzzleState>();

			PuzzleState root = new PuzzleState(puzzle, heuristicValue, null,1000);
			root.setTime(System.currentTimeMillis());
			closedState.add(root);

			state = Search(root, openState, closedState);
			state.setTime(System.currentTimeMillis());
			listSolvedStates.add(state);
			
		}
		return listSolvedStates;
	}
	
	private PuzzleState Search(PuzzleState state, Queue<PuzzleState> openState, List<PuzzleState> closedState) {

		while (state.getHeuristicValue() != 0){
			int blankPosition = findBlankSpace(state.getPuzzle());
			ArrayList<String> validMoves = VerifyLegalMove(blankPosition);

			for (String validMove : validMoves) {
				PuzzleState newState = CreateNewState(state, validMove, blankPosition);
				if (VerifyStateIsNotInClosedQueue(newState, closedState)) {
					openState.add(newState);
				}
			}

			state = openState.remove();
			closedState.add(state);
		}

		System.out.println(closedState.size());	
		System.out.println(openState.size());	
		return state;
	}


	private float CalculateHeuristic(char[] puzzle) {
		char[] puzzle1=puzzle.clone();
		char[] puzzle2=puzzle.clone();
		float heuristic1=0,heuristic2=0, manhattanDistanceTemp = 0;
		int x,bottomRowIndex,topRowIndex;
		for(int i= 0;i<5;i++)
		{	
			float manhattanDistancePoint=4.5f;//10 best time shit heur
			int indexToRemove=i;
			if(puzzle1[i]!='x'){
				for(int j=i+1;j<puzzle1.length;j++){
					if(puzzle1[j]==puzzle1[i]){
						x=(j%5)-(i%5);
						bottomRowIndex=i+10;
						manhattanDistanceTemp=Math.abs((j%5)-(bottomRowIndex%5))+Math.abs(((j-x)-bottomRowIndex)/5);
						manhattanDistanceTemp+=manhattanDistanceTemp*0.3;//Added weight reduces search space grreatly but from testing gives same solution path than without it.
						if(manhattanDistanceTemp<manhattanDistancePoint)
						{
							manhattanDistancePoint=manhattanDistanceTemp;
							indexToRemove=j;
						}
						
						//System.out.println("j: "+j+" i: "+i+"  ManDist: "+manhattanDistanceTemp);
					}
				}
			}
			heuristic1+=manhattanDistancePoint;
			puzzle1[indexToRemove]='x';
		}
		for(int i=14;i>9;i--)
		{	
			float manhattanDistancePoint=4.5f;
			int indexToRemove=i;
			if(puzzle2[i]!='x'){//56 1024
				for(int j=i-1;j>0;j--){
					if(puzzle2[j]==puzzle2[i]){
						x=(j%5)-(i%5);
						topRowIndex=i-10;
						manhattanDistanceTemp=Math.abs((j%5)-(topRowIndex%5))+Math.abs(((j-x)-topRowIndex)/5);
						manhattanDistanceTemp+=manhattanDistanceTemp*0.3;//Added weight reduces search space grreatly but from testing gives same solution path than without it.
						if(manhattanDistanceTemp<manhattanDistancePoint)
						{
							manhattanDistancePoint=manhattanDistanceTemp;
							indexToRemove=j;
						}
						
						//System.out.println("j: "+j+" i: "+i+"  ManDist: "+manhattanDistanceTemp);
					}
				}
			}
			heuristic2+=manhattanDistancePoint;
			puzzle2[indexToRemove]='x';
		}

		if(heuristic1<heuristic2)
			return heuristic1;
		else
			return heuristic2;
	}

	private ArrayList<String> VerifyLegalMove(int blank) {
		ArrayList<String> legalMoves = new ArrayList<String>();

		if (blank > 4)
			legalMoves.add("up");
		if (blank != 0 && blank != 5 && blank != 10)
			legalMoves.add("left");
		if (blank < 10)
			legalMoves.add("down");
		if (blank != 4 && blank != 9 && blank != 14)
			legalMoves.add("right");

		return legalMoves;
	}

	private PuzzleState CreateNewState(PuzzleState state, String move, int blankPosition) {
		char[] newPuzzle = Arrays.copyOf(state.getPuzzle(), state.getPuzzle().length);
		char storeChar;
		int blank=0;
		switch (move) {
		case "up":
			storeChar = newPuzzle[blankPosition - 5];
			newPuzzle[blank= blankPosition - 5] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		case "left":
			storeChar = newPuzzle[blankPosition - 1];
			newPuzzle[blank =blankPosition - 1] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		case "down":
			storeChar = newPuzzle[blankPosition + 5];
			newPuzzle[blank = blankPosition + 5] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		case "right":
			storeChar = newPuzzle[blankPosition + 1];
			newPuzzle[blank = blankPosition + 1] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		}

		return new PuzzleState(newPuzzle, CalculateHeuristic(newPuzzle), state, blank);
	}

	private boolean VerifyStateIsNotInClosedQueue(PuzzleState state, List<PuzzleState> closedState) {
		char[] statePuzzle = state.getPuzzle();

		for (PuzzleState closedPuzzleState : closedState) {
			char[] closedPuzzle = closedPuzzleState.getPuzzle();
			if (state.getHeuristicValue() == closedPuzzleState.getHeuristicValue()) {
				for (int i = 0; i < statePuzzle.length; i++) {
					if (statePuzzle[i] != closedPuzzle[i]) {
						break;
					}

					if (i == statePuzzle.length - 1) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	private boolean VerifyStateIsNotInOpenQueue(PuzzleState state, Queue<PuzzleState> openState) {
		char[] statePuzzle = state.getPuzzle();

		for (PuzzleState openPuzzleState : openState) {
			char[] closedPuzzle = openPuzzleState.getPuzzle();
			if (state.getHeuristicValue() == openPuzzleState.getHeuristicValue()) {
				for (int i = 0; i < statePuzzle.length; i++) {
					if (statePuzzle[i] != closedPuzzle[i]) {
						break;
					}

					if (i == statePuzzle.length - 1) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	private int findBlankSpace(char[] puzzle) {
		int blank = 0;
		for (int i = 0; i < 15; i++) {
			if (puzzle[i] == 'e') {
				blank = i;
				break;
			}

		}
		return blank;
	}
}
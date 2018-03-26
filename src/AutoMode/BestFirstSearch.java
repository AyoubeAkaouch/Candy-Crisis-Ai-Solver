package AutoMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch {

	public BestFirstSearch() {
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
			int heuristicValue = CalculateHeuristic(puzzle);
			HeuristicComparator comparator = new HeuristicComparator();
			PuzzleState state;

			System.out.println("Solving puzzle");
			Queue<PuzzleState> openState = new PriorityQueue<PuzzleState>(comparator);
			Queue<PuzzleState> closedState = new PriorityQueue<PuzzleState>(comparator);

			PuzzleState root = new PuzzleState("Rootstate", puzzle, heuristicValue, null, null);

			closedState.add(root);

			state = Search(root, openState, closedState, 0);
			listSolvedStates.add(state);
		}
		return listSolvedStates;
	}

	private PuzzleState Search(PuzzleState state, Queue<PuzzleState> openState, Queue<PuzzleState> closedState,
			int puzzleStateNumber) {
		
		while (state.getHeuristicValue() != 0){
			int blankPosition = findBlankSpace(state.getPuzzle());
			ArrayList<String> validMoves = VerifyLegalMove(blankPosition);

			for (String validMove : validMoves) {
				puzzleStateNumber++;
				PuzzleState newState = CreateNewState(state, validMove, blankPosition, puzzleStateNumber);
				if (VerifyStateIsNotInClosedQueue(newState, closedState)) {
					openState.add(newState);
				}
			}

			state = openState.remove();
			closedState.add(state);
		}
			
		System.out.println(closedState.size());	
		return state;
	}

	private int CalculateHeuristic(char[] puzzles) {
		int heuristic=0, manhattanDistanceTemp = 0;
		int x,bottomRowIndex;
		for(int i= 0;i<5;i++)
		{	
			int manhattanDistancePoint =99999999, indexToRemove=i;
			for(int j=i+1;j<puzzles.length;j++){
				if(puzzles[j]==puzzles[i]){
					x=(j%5)-(i%5);
					bottomRowIndex=i+10;
					manhattanDistanceTemp=Math.abs((j%5)-(bottomRowIndex%5))+Math.abs(((j-x)-bottomRowIndex)/5);
					if(manhattanDistanceTemp<manhattanDistancePoint)
					{
						manhattanDistancePoint=manhattanDistanceTemp;
						indexToRemove=j;
					}
					//System.out.println("j: "+j+" i: "+i+"  ManDist: "+manhattanDistanceTemp);
				}
			}
			if(manhattanDistancePoint==99999999){
				heuristic+=15;
				puzzles[indexToRemove]='x';
			}
			else
			{
				heuristic+=manhattanDistancePoint;
						puzzles[indexToRemove]='x';
			}
			
		}
		
		return heuristic;
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

	private PuzzleState CreateNewState(PuzzleState state, String move, int blankPosition, int puzzleStateNumber) {
		char[] newPuzzle = Arrays.copyOf(state.getPuzzle(), state.getPuzzle().length);
		char storeChar;

		switch (move) {
		case "up":
			storeChar = newPuzzle[blankPosition - 5];
			newPuzzle[blankPosition - 5] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		case "left":
			storeChar = newPuzzle[blankPosition - 1];
			newPuzzle[blankPosition - 1] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		case "down":
			storeChar = newPuzzle[blankPosition + 5];
			newPuzzle[blankPosition + 5] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		case "right":
			storeChar = newPuzzle[blankPosition + 1];
			newPuzzle[blankPosition + 1] = 'e';
			newPuzzle[blankPosition] = storeChar;
			break;
		}

		return new PuzzleState("state" + puzzleStateNumber, newPuzzle, CalculateHeuristic(newPuzzle), state, move);
	}

	private boolean VerifyStateIsNotInClosedQueue(PuzzleState state, Queue<PuzzleState> closedState) {
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
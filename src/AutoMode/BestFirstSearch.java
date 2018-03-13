package AutoMode;

import java.util.ArrayList;

public class BestFirstSearch {

	/*
	 * Steps:
	 * 1: Verify legal moves based on blank position
	 * 2: Generate State object with puzzle position and heuristic value.
	 * 3: Remove states that are in the closed queue
	 * 4: Put all valid states in the open queue and sort queue
	 * 5: Select best heuristic value state from open queue and remove it from queue
	 * 6: Switch state, put previous state in closed queue
	 */
	
	private int CalculateHeuristic(char[] puzzles)
	{
		int heuristicValue = 0;
		for (int i=0;i<5;i++){
			if(puzzles[i]!=puzzles[10+i]){
				heuristicValue++;
			}
		}
		
		return heuristicValue;
	}
	
	private void GenerateNextMoves(char[] puzzle,String move,int blankSpace)
	{
		char storeChar;
		switch (move) {
		case "w":
			storeChar=puzzle[blankSpace-5];
			puzzle[blankSpace-5]='e';
			puzzle[blankSpace]=storeChar;
			break;
		case "a":
			storeChar=puzzle[blankSpace-1];
			puzzle[blankSpace-1]='e';
			puzzle[blankSpace]=storeChar;
			break;
		case "s":
			storeChar=puzzle[blankSpace+5];
			puzzle[blankSpace+5]='e';
			puzzle[blankSpace]=storeChar;
			break;
		case "d":
			storeChar=puzzle[blankSpace+1];
			puzzle[blankSpace+1]='e';
			puzzle[blankSpace]=storeChar;
			break;
		}
		
		
	}
	
	private ArrayList<String> VerifyLegalMove(int blank){
		ArrayList<String> legalMoves = new ArrayList<String>();
		
		if(blank<=0 || blank>=4)
			legalMoves.add("w");
		if(blank!= 0 && blank!=5 && blank!=10)
			legalMoves.add("a");
		if(blank>=14 || blank<=10)
			legalMoves.add("s");
		if(blank!=4 && blank!=9 && blank!=14)
			legalMoves.add("d");
			
		return legalMoves;
	}
}

package AutoMode;

import java.util.ArrayList;

public class PuzzleState {

	//Add parent
	//add name
	
	public PuzzleState(char[] puzzle, int heuristicValue)
	{
		this.puzzle = puzzle;
		this.heuristicValue = heuristicValue;
	}
	
	char[] puzzle;
	
	int heuristicValue;
	
	public char[] getPuzzle()
	{
		return puzzle;
	}
	
	public void setPuzzle(char[] puzzle)
	{
		this.puzzle = puzzle;
	}
	
	public int getHeuristicValue()
	{
		return heuristicValue;
	}
	
	public void setHeuristicValue(int value)
	{
		this.heuristicValue = value;
	}
}

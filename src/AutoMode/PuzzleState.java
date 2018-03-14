package AutoMode;

import java.util.ArrayList;

public class PuzzleState {

	public PuzzleState(String name, char[] puzzle, int heuristicValue, PuzzleState parentState)
	{
		this.name = name;
		this.puzzle = puzzle;
		this.heuristicValue = heuristicValue;
		this.parentState = parentState;
	}
	
	private String name;
	
	private char[] puzzle;
	
	private int heuristicValue;
	
	private PuzzleState parentState;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
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
	
	public PuzzleState getParentState()
	{
		return parentState;
	}
	
	public void setParentState(PuzzleState state)
	{
		parentState = state;
	}
}

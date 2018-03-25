package AutoMode;

import java.util.ArrayList;
import java.util.List;

public class PuzzleState {

	public PuzzleState(String name, char[] puzzle, int heuristicValue, PuzzleState parentState, String move)
	{
		this.name = name;
		this.puzzle = puzzle;
		this.heuristicValue = heuristicValue;
		this.parentState = parentState;
		this.move=move;
		this.time = System.currentTimeMillis();
		setCost();
	}

	private String name;

	private char[] puzzle;

	private int heuristicValue;

	private PuzzleState parentState;

	private String move;

	private long time;
	
	private int cost;

	public PuzzleState getParent()
	{
		return parentState;
	}

	public long getTime(){
		return time;
	}

	public String getMove()
	{
		return move;
	}

	//Will return list with last move at position 0, will have to iterate through 
	//list in decreasing order to get it from first move to last.
	public List<String> getPreviousMoves(List<String> allMoves){
		if(this.getMove()==null)
			return allMoves;
		allMoves.add(this.getMove());
		return parentState.getPreviousMoves(allMoves);
	}

	//Will return list with last move at position 0, will have to iterate through 
	//list in decreasing order to get it from first move to last.
	public List<PuzzleState> getPreviousStates(List<PuzzleState> allStates){
		if(this.getParent()==null){
			allStates.add(this);
			return allStates;
		}
		allStates.add(this);
		return parentState.getPreviousStates(allStates);
	}
	
	public void setCost(){
		if(this.parentState==null)
			this.cost=0;
		else
			this.cost= parentState.costHeursiticTotal();
	}

	public int costHeursiticTotal(){
		return cost+this.heuristicValue;
	}
	public int getCost()
	{
		return cost;
	}
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

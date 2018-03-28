package AutoMode;

import java.util.ArrayList;
import java.util.List;

public class PuzzleState {

	public PuzzleState(char[] puzzle, float heuristicValue, PuzzleState parentState, int move)
	{
		this.puzzle = puzzle;
		this.heuristicValue = heuristicValue;
		this.parentState = parentState;
		this.move=move;
		this.time = 0;
		setCost();
	}

	private char[] puzzle;

	private float heuristicValue;

	private PuzzleState parentState;

	private int move;

	private long time;
	
	private float cost;

	public PuzzleState getParent()
	{
		return parentState;
	}

	public long getTime(){
		return time;
	}

	public int getMove()
	{
		return move;
	}

	//Will return list with last move at position 0, will have to iterate through 
	//list in decreasing order to get it from first move to last.
	public List<Integer> getPreviousMoves(List<Integer> allMoves){
		if(this.getParent()==null)
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
			this.cost= (parentState.cost+1)*0.983f;//0.997: 346moves/263sec 0.99: 360moves/23.5sec !!!!!0.987f;
	}

	public void setTime(long time){
		this.time=time;
	}
	
	public float costHeursiticTotal(){
		return cost+this.heuristicValue;
	}
	public float getCost()
	{
		return cost;
	}
	
	public char[] getPuzzle()
	{
		return puzzle;
	}

	public void setPuzzle(char[] puzzle)
	{
		this.puzzle = puzzle;
	}

	public float getHeuristicValue()
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

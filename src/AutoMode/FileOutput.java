package AutoMode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOutput {

	private List<PuzzleState> states;
	
	private int totalMoves;
	
	public FileOutput (List<PuzzleState> states)
	{
		this.states=states;
		outputMoves();
		visualTrace();
	}
	
	private void outputMoves()
	{
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			
			fw = new FileWriter("output.txt");
			bw = new BufferedWriter(fw);
			int count=1;
			for(PuzzleState puzzle: states){
				long endTime;
				List<String> moves = new ArrayList<String>();
				List<PuzzleState> solutionPath = new ArrayList<PuzzleState>();
			
				//getting solution path for first puzzle
				solutionPath = puzzle.getPreviousStates(solutionPath);
				
				bw.write("Solution for puzzle #"+count);
				count++;
				
				//Getting the time that it was solved
				endTime=getSolvingTime(solutionPath);
				
				//Getting set of moves to get solution path	
				moves = puzzle.getPreviousMoves(moves);
				
				//Adding the total number of moves to the list
				totalMoves+= moves.size();
				bw.write("\nMoves made to solve: ");
				for	(int i = moves.size()-1;i>=0;i--){
					String currentMove=moves.get(i);
					bw.write(currentMove+"  ");
				}
				bw.newLine();
				bw.write("This puzzle was solved in: "+ endTime+"ms.\n\n");
				
				
			}
			bw.write("All the puzzles were solved in "+ totalMoves+" moves!");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	private void visualTrace()
	{
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			
			fw = new FileWriter("VisualTrace.txt");
			bw = new BufferedWriter(fw);
			int count=1;
			for(PuzzleState puzzle: states){
				List<PuzzleState> solutionPath = new ArrayList<PuzzleState>();
				
				//getting solution path for first puzzle
				solutionPath = puzzle.getPreviousStates(solutionPath);
				
				bw.write("Visual trace for puzzle #"+count);
				count++;
				bw.newLine();
			
				for	(int i = solutionPath.size()-1;i>=0;i--){
					char[] currentVisual=solutionPath.get(i).getPuzzle();
					writePuzzle(currentVisual,bw);
				}
				bw.newLine();		
				
			}
			
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	private long getSolvingTime(List<PuzzleState> solutionPath){
		long startTime = solutionPath.get(solutionPath.size()-1).getTime();
		long endTime = solutionPath.get(0).getTime();
		 
		return endTime-startTime;
	}
	
	private void writePuzzle(char[] puzzle, BufferedWriter bw) throws IOException
	{
		for (int i=0;i<15;i++){

			if(i%5 == 0){
				if(puzzle[i]=='e')
					bw.write("\n  |");
				else
					bw.write("\n"+puzzle[i]+" |");
			}
			else{
				if(puzzle[i]=='e')
					bw.write("  |");
				else
					bw.write(puzzle[i]+" |");
			}

		}
		bw.newLine();
	}
	private static void displayPuzzle(char[] puzzle)
	{
		for (int i=0;i<15;i++){

			if(i%5 == 0){
				if(puzzle[i]=='e')
					System.out.print("\n  |");
				else
					System.out.print("\n"+puzzle[i]+" |");
			}
			else{
				if(puzzle[i]=='e')
					System.out.print("  |");
				else
					System.out.print(puzzle[i]+" |");
			}

		}
		System.out.println("");
	}
	
	
}

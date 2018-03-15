package AutoMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CompProject472.PuzzleReader;

public class Main {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		List<PuzzleState> solvedStates= new ArrayList<PuzzleState>();
		
		System.out.println("Thanks for selecting Automatic mode please enter the path of your puzzles");
		String path = userInput.nextLine();

		ArrayList<char[]> puzzleSet = PuzzleReader.retrievePuzzles(path);
		
		BestFirstSearch automaticSearch = new BestFirstSearch();
		solvedStates=automaticSearch.Start(puzzleSet);
		FileOutput output= new FileOutput(solvedStates);
		
		System.out.println("Thanks for playing Candy Crisis!\n"
				+ "The results will be in the output and visualTrace files generated in this folder.");
		
		userInput.close();
	}

}

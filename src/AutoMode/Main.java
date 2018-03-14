package AutoMode;

import java.util.ArrayList;
import java.util.Scanner;

import CompProject472.PuzzleReader;

public class Main {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Thanks for selecting Automatic mode please enter the path of your puzzles");
		String path = userInput.nextLine();

		ArrayList<char[]> puzzleSet = PuzzleReader.retrievePuzzles(path);
		
		BestFirstSearch automaticSearch = new BestFirstSearch();
		automaticSearch.Start(puzzleSet);
		
		System.out.println("Thanks for playing Candy Crisis!");
		
		userInput.close();
	}

}

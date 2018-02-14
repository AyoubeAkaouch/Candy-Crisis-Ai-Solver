package CompProject472;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ManualMode {

	public static void start(Scanner userInput) {
		char[] puzzle;
		String move;
		int blankSpace;


		System.out.println("Thanks for selecting manual mode please enter the path of your puzzles");
		userInput.nextLine();
		String path = userInput.nextLine();

		ArrayList<char[]> puzzleSet = PuzzleReader.retrievePuzzles(path);


		if(puzzleSet.size()<2)
			puzzle=puzzleSet.get(0);
		else{
			System.out.println("I found multiple puzzles in this file please enter number the one you want to solve:\n");
			for(int i=0;i<puzzleSet.size();i++){
				System.out.println("("+(i+1)+") "+ Arrays.toString(puzzleSet.get(i))+" ;");
			}
			int selected=userInput.nextInt();
			puzzle=puzzleSet.get(selected-1);

		}
		
		System.out.println("Let's play! Use W A S D to move the empty tile around.\n(Press enter after each key press to input it.)");
		userInput.nextLine();

		while (!isPuzzleSolved(puzzle)){
			displayPuzzle(puzzle);
			move= userInput.nextLine();
			
			blankSpace=findBlankSpace(puzzle);
			
			if(isMoveLegal(move,blankSpace))
			{
				makeMove(puzzle,move,blankSpace);
			}
			else{
				System.out.println("This move is illegal please enter another one");
			}
		}
		
		displayPuzzle(puzzle);
		System.out.println("Congratulation on solving the puzzle!!!");

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

	private static boolean isPuzzleSolved(char[] puzzle){
		boolean solved = true;
		for (int i=0;i<5;i++){
			if(puzzle[i]!=puzzle[10+i]){
				solved=false;
				break;
			}
		}

		return solved;
	}

	private static boolean isMoveLegal(String move,int blank){
		boolean isLegal=true;
		switch (move) {
		case "w":
			if(blank>=0&&blank<=4)
				isLegal=false;
			break;
		case "a":
			if(blank==0||blank==5||blank==10)
				isLegal=false;
			break;
		case "s":
			if(blank<=14&&blank>=10)
				isLegal=false;
			break;
		case "d":
			if(blank==4||blank==9||blank==14)
				isLegal=false;
			break;
		default:
			isLegal=false;
			break;
		}

		return isLegal;
	}

	private static void makeMove(char[] puzzle,String move,int blankSpace){
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
	private static int findBlankSpace(char[] puzzle){
		int blank=0;
		for(int i=0;i<15;i++){
			if(puzzle[i]=='e'){
				blank=i;
				break;
			}

		}	
		return blank;
	}

}

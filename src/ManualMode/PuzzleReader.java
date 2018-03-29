package ManualMode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PuzzleReader {

	static public ArrayList<char[]> retrievePuzzles (String fileName){

		ArrayList<char[]> puzzles= new ArrayList<char[]>();
		BufferedReader bufferedReader = textReader(fileName);
		String line=null;

		try {
			
			while((line = bufferedReader.readLine()) != null) {
				puzzles.add(line.replaceAll("\\s+","").toCharArray());
			}

			bufferedReader.close();

		} catch (IOException e) {
			System.out.println("Error reading file '" + fileName + "'");
		}

		return puzzles;

	}

	private static BufferedReader textReader(String fileName){
		BufferedReader bufferedReader=null;
		try {
			FileReader fileReader =	new FileReader(fileName);
			bufferedReader =  new BufferedReader(fileReader);
			return bufferedReader;
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		return bufferedReader;
	}

}

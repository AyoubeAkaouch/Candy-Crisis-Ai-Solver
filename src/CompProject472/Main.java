package CompProject472;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		int mode=1;
		
		System.out.println("Welcome to Candy Crisis!\n Which mode do you want to run, please write 1 or 2 ?"
				+ "\n(1) Manual mode \n(2) Automatic mode (Not yet implemented)");
		mode = userInput.nextInt();
		if(mode==1)
			ManualMode.start(userInput);
		
		System.out.println("Thanks for playing Candy Crisis!");
		
		userInput.close();

	}

}

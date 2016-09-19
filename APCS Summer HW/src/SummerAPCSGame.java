/*
 * Rock-Paper-Scissors APCS Homework
 * 
 * @author Satyendra Emani
 */

import java.util.Scanner;

public class SummerAPCSGame {
	static final int MAX_PLAYS = 10; //Max amount of plays
	static int[] winTieLoss = new int[MAX_PLAYS + 1]; //Stores wins, ties, and losses
	static String[] results = new String[MAX_PLAYS + 1]; //Represents wins, ties, and losses as strings
	static int timesPlayed = 0; //Stores how many times the game has been played
	static int wins = 0; //Stores amount of wins for results
	static int losses = 0; //Stores amount of losses for results

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int computerChoice = 0;
		int playerChoice = 0;

		System.out.println("Welcome to Rock, Paper, Scissors.");
		//Print greeting, start game
		//Game goes on for however many plays is set in
		//the MAX_PLAYS constant.
		
		for (int i = 0; i < MAX_PLAYS; i++) {
			System.out.println("Choose your weapon.");
			System.out.println("1. Rock");
			System.out.println("2. Paper");
			System.out.println("3. Scissors");
			System.out.println("Type in \"-1\" to quit.");
			playerChoice = keyboard.nextInt();
			//Prompt for and accept choice from player
			
			if (playerChoice > 0 && playerChoice < 4) {
				computerChoice = (int) ((Math.random() * 3 - 1 + 1) + 1);
				findWinner(computerChoice, playerChoice);
				timesPlayed++;
				//Play the game
			}
			else if (playerChoice == -1) {
				i = MAX_PLAYS;
				//Exit the game by meeting the end requirements for the for loop
			}
			else {
				System.err.println("Invalid Choice!");
				i--;
				//Tell the user that the choice is invalid, prevent 
				//loop from ending early by decrementing i.
			}
		}
		calcWinTieLoss();
		printResults();
	}
	
	/**
	 * Converts the wins, ties, and losses
	 * to strings.
	 * 
	 * When a player plays, the scores stored in
	 * winTieLoss[] is defined as this:
	 * 
	 * -1 = loss (for player)
	 * 0 = tie (for player)
	 * 1 = win (for player)
	 */
	private static void calcWinTieLoss() {
		for (int i = 0; i < timesPlayed; i++) {
			switch (winTieLoss[i]) {
			case -1:
				results[i] = "Loss";
				break;
			case 0:
				results[i] = "Tie";
				break;
			case 1:
				results[i] = "Win";
				break;
			}
		}

	}
	
	/**
	 * Prints out the results
	 */
	private static void printResults() {
		System.out.println("Thank you for playing!");
		System.out.printf("%20s%20s", "Turn", "Result");
		//Setup header for printing out table of results
		
		for (int i = 0; i < timesPlayed; i++) {
			System.out.println();
			System.out.printf("%20s%20s", "Turn " + (1 + i) + ":", results[i]);
		}
		//Prints out tables of results

		System.out.println();
		System.out.println();
		System.out.println("Total wins: " + wins);
		System.out.println("Total losses: " + losses);
		//Tells total wins and losses of user
		
		//Tells user whether they won, or lost
		//a majority of the rounds
		if (wins > losses) {
			System.out.println("You won the majority of the rounds.");
		}
		if(losses > wins){
			System.out.println("You lost the majority of the rounds.");
		}
		if(losses == wins){
			System.out.println("You didn't win or lose a majority.");
		}
		

	}
	
	/**
	 * Calculates the winner based on what the player and computer chose.
	 * @param computerChoice: computer's choice
	 * @param playerChoice: player's choice
	 */
	private static void findWinner(int computerChoice, int playerChoice) {
		
		//compares computer's choice to player's choice
		//1 = rock, 2 = paper, 3 = scissors
		//When scoring,
		//-1 = loss (for player)
		//0 = tie (for player)
		//1 = win (for player)
		switch (computerChoice) {
		case 1:
			if (playerChoice == 1) {
				System.out.println("The computer chose rock. You chose rock. It's a tie.");
				System.out.println();
				winTieLoss[timesPlayed] = 0;
			}
			if (playerChoice == 2) {
				System.out.println("The computer chose rock. You chose paper. You win.");
				System.out.println();
				winTieLoss[timesPlayed] = 1;
				wins++;
			}
			if (playerChoice == 3) {
				System.out.println("The computer chose rock. You chose scissors. You lose.");
				System.out.println();
				winTieLoss[timesPlayed] = -1;
				losses++;
			}
			break;
		case 2:
			if (playerChoice == 1) {
				System.out.println("The computer chose paper. You chose rock. You lose.");
				System.out.println();
				winTieLoss[timesPlayed] = -1;
				losses++;
			}
			if (playerChoice == 2) {
				System.out.println("The computer chose paper. You chose paper. It's a tie.");
				System.out.println();
				winTieLoss[timesPlayed] = 0;
			}
			if (playerChoice == 3) {
				System.out.println("The computer chose paper. You chose scissors. You win.");
				System.out.println();
				winTieLoss[timesPlayed] = 1;
				wins++;
			}
			break;
		case 3:
			if (playerChoice == 1) {
				System.out.println("The computer chose scissors. You chose rock. You win.");
				System.out.println();
				winTieLoss[timesPlayed] = 1;
				wins++;
			}
			if (playerChoice == 2) {
				System.out.println("The computer chose scissors. You chose paper. You lose");
				System.out.println();
				winTieLoss[timesPlayed] = -1;
				losses++;
			}
			if (playerChoice == 3) {
				System.out.println();
				System.out.println("The computer chose scissors. You chose scissors. It's a tie");
				winTieLoss[timesPlayed] = 0;
			}
			break;
		}

	}
}

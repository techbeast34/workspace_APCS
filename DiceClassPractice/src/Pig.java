import java.util.Scanner;

public class Pig {

	static int amtPointsPlayer = 0;
	static int amtPointsComputer = 0;
	static int amtPointsRound = 0;

	public static void main(String[] args) {
		

		boolean playerActive = false;
		playerActive = promptPlayer();
		while (playerActive && (amtPointsRound < 20)) {
			amtPointsRound += rollDice();
			if(amtPointsRound > 20){
				playerActive = false;
			}
		}
		
		amtPointsPlayer += amtPointsRound;
		amtPointsRound = 0;
		
		while(!playerActive){
			amtPointsRound += rollDice();
			if(amtPointsRound > 20){
				playerActive = true;
			}
		}
		amtPointsComputer += amtPointsRound;
		amtPointsRound = 0;

	}

	private static int rollDice() {
		int amtPoints = 0;
		Die die1 = new Die();
		Die die2 = new Die();
		die1.roll();
		die2.roll();
		amtPoints += die1.getFace() + die2.getFace();
		if (die1.getFace() == 1 || die2.getFace() == 1) {
			amtPoints = 0;
			amtPointsRound = 0;
		}
		if (die1.getFace() == 1 && die1.isDoubles(die2)) {
			amtPoints = 0;
			amtPointsPlayer = 0;
		}

		return amtPoints;
	}

	private static boolean promptPlayer() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Would you like to play or turn over to computer?");
		System.out.println("1. Play");
		System.out.println("2. Turn Over");
		int choice = keyboard.nextInt();
		switch (choice) {
		case 1:
			return true;
		case 2:
			return false;
		}
		return false;
	}

}

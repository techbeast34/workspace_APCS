
public class Craps {

	public static void main(String[] args) {
		Die dice1 = new Die();
		Die dice2 = new Die();
		
		dice1.roll();
		dice2.roll();
		
		if((dice1.getFace() + dice2.getFace()) == 7){
			System.out.println("You won!");
			System.out.println("Dice 1: " + dice1.getFace());
			System.out.println("Dice 2: " + dice2.getFace());
		}
		
		if((dice1.getFace() + dice2.getFace()) != 7){
			System.out.println("You lost!");
			System.out.println("Dice 1: " + dice1.getFace());
			System.out.println("Dice 2: " + dice2.getFace());
		}
	}

}

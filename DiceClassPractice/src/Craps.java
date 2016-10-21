
public class Craps {
	
	public final static String[] faces = 
		{
		" _______\n" +		
		"|       |\n" +
		"|   o   |\n" +
		"|_______|\n",
		
		" _______\n" +		
		"|       |\n" +
		"| o   o |\n" +
		"|_______|\n",
				
		" _______\n" +		
		"| o     |\n" +
		"|   o   |\n" +
		"|_____o_|\n",
		
		" _______\n" +		
		"| o   o |\n" +
		"|       |\n" +
		"|_o___o_|\n",
		
		" _______\n" +		
		"| o   o |\n" +
		"|   o   |\n" +
		"|_o___o_|\n",
		
		" _______\n" +		
		"| o   o |\n" +
		"| o   o |\n" +
		"|_o___o_|\n"
		};
	
	
	public static void main(String[] args) {
		Die dice1 = new Die();
		Die dice2 = new Die();
		
		dice1.roll();
		dice2.roll();
		
		if((dice1.getFace() + dice2.getFace()) == 7){
			System.out.println("You won!");
			System.out.println("Dice 1: " + dice1.getFace());
			System.out.println("Dice 2: " + dice2.getFace());
			System.out.println(faces[dice1.getFace() - 1] + faces[dice2.getFace() - 1]);
		}
		
		if((dice1.getFace() + dice2.getFace()) != 7){
			System.out.println("You lost!");
			System.out.println("Dice 1: " + dice1.getFace());
			System.out.println("Dice 2: " + dice2.getFace());
			System.out.println(faces[dice1.getFace() - 1] + faces[dice2.getFace() - 1]);
		}
	}

}

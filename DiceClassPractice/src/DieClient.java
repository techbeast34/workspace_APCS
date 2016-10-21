
public class DieClient {
	
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
		
		Die die1 = new Die();
		Die die2 = new Die();
		
		
		int count = 1;
		
		die1.roll();
		die2.roll();
		
		while(!die1.isDoubles(die2)){
			die1.roll();
			die2.roll();
			System.out.println("Roll " + count + ":");
			System.out.println("Die 1: " + die1.getFace() + "\n"+"Die 2: " + die2.getFace());
			System.out.println(faces[die1.getFace() - 1] + faces[die2.getFace() - 1]);
			count++;
			
		}
		
		if(die1.isDoubles(die2)){
			
			System.out.println("You rolled doubles on roll " + (count - 1) + "!");
			System.out.println("Die 1: " + die1.getFace() + "\n"+"Die 2: " + die2.getFace());
		}

	}


}

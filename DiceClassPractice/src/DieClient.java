
public class DieClient {
	
	
	public static void main(String[] args) {
		
		Die die1 = new Die();
		Die die2 = new Die();
		
		
		int count = 1;
		
		die1.roll();
		die2.roll();
		
		while(die1.getFace() != die2.getFace()){
			die1.roll();
			die2.roll();
			System.out.println("Roll " + count + ":");
			System.out.println("Die 1: " + die1.getFace() + "\n"+"Die 2: " + die2.getFace());
			System.out.println();
			count++;
			
		}
		
		if(die1.getFace() == die2.getFace()){
			
			System.out.println("You rolled doubles on roll " + (count - 1) + "!");
			System.out.println("Die 1: " + die1.getFace() + "\n"+"Die 2: " + die2.getFace());
		}

	}


}


public class DieClient {
	
	
	public static void main(String[] args) {
		Die die1 = new Die();
		Die die2 = new Die();
		int dieFace1 = 0;
		int dieFace2 = 0;
		int count = 1;
		
		die1.roll();
		dieFace1 = die1.getFace();
		die2.roll();
		dieFace2 = die2.getFace();
		
		while(dieFace1 != dieFace2){
			die1.roll();
			dieFace1 = die1.getFace();
			die2.roll();
			dieFace2 = die2.getFace();
			System.out.println("Roll " + count + ":");
			System.out.println("Die 1: " + dieFace1 + "\n"+"Die 2: " + dieFace2);
			System.out.println();
			count++;
			
		}
		
		if(dieFace1 == dieFace2){
			
			System.out.println("You rolled doubles on roll " + (count - 1) + "!");
			System.out.println("Die 1: " + dieFace1 + "\n"+"Die 2: " + dieFace2);
		}

	}


}
/**
 * Dice
 * 
 * @author Satyendra Emani
 *
 */


public class Die {
	private int face = 0;
	private int numSides = 6;
	
	public Die(int sides){
		numSides = sides;
	}
	
	public Die(){
		
	}
	
	
	public void roll(){
		face = (int) (Math.random() * (numSides - 1 + 1)) + 1;
	}
	
	public int getFace(){
		return face;
	}
	
	public String toString(){
		return "Face: " + face;
	}
	
	public boolean isDoubles(Die dice){
		if(dice.getFace() == getFace())
			return true;
		
		return false;
	}
}

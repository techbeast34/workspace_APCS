/**
 * Dice
 * 
 * @author Satyendra Emani
 *
 */
public class Die {
	private int face = 0;
	
	public void roll(){
		face = (int) (Math.random() * (6 - 1 + 1)) + 1;
	}
	
	public int getFace(){
		return face;
	}
	
	public String toString(){
		return "Face: " + face;
	}
	
}

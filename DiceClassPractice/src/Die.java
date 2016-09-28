/**
 * Dice
 * 
 * @author Satyendra Emani
 *
 */
public class Die {
	private static int face = 0;
	
	public static void Roll(){
		face = (int) (Math.random() * 6);
	}
	
	public static int getFace(){
		return face;
	}
	
}

/**
 * This creates a single card
 * @author Montek
 *
 */
public class Card {
	String face;
	String suit;
	
	/**
	 * the constructor instantiating card object with value, and suit
	 * @param face the face value 
	 * @param suit the suit of card
	 */
	public Card(String face, String suit){
		this.face = face;
		this.suit = suit;
	}
	/**
	 * returns face value
	 * @return String object containing value
	 */
	public String getFace(){
		return face;
	}
	/**
	 * returns the suit type 
	 * @return string object containing suit type
	 */
	public String getSuit(){
		return suit;
		
	}
	/**
	 * returns a string of face value and suit
	 */
	public String toString(){
		return face+" of "+suit;
	}
}


import java.util.Random;


public class DeckOfCards
{
	/* You should use these arrays when making your cards */
	public static final String[] SUITS = {"Hearts", "Spades", "Clubs", "Diamonds"};
	public static final String[] FACES = {"Ace", "2", "3", "4", "5", "6", "7",
		"8", "9", "10", "Jack", "Queen", "King"};	

	private int cardsLeft = 52;
	
	Card [] deck = new Card[getCardsLeft()];
	Random rand = new Random();
	/**
	 * 
	 * constructor creates and fills an array (deck of cards) with card objects representing a deck
	 */
	public DeckOfCards()
	{
		
		int location = 0;
		
		for(int x=0;x<4;x++){
			for (int y=0;y<13;y++){
				deck[location] = new Card(FACES[y],SUITS[x]);
				//System.out.println(SUITS[x]+" "+FACES[y]);
				location++;
			}
		}
			
		
	}
	/**
	 * shuffles the cards in the array
	 */
	public void shuffle(){
		for(int n=0;n<51;n++) {
			int k = rand.nextInt(52);
			
			Card temp = deck[n];
		    deck[n] = deck[k];
		    deck[k] = temp;
		    
		}
	}
	/**
	 * returns a card from the deck or null if no cards are left
	 * @return card from deck
	 */
	public Card deal(){
		
		
		cardsLeft--;
		if (cardsLeft>0){
			return deck[cardsLeft];	
			
		}else{
			
			return null;
		}
	}
	
	
	/**
	 * 
	 * @return returns integer value of cards left in deck
	 */
	 public int getCardsLeft(){
		 return cardsLeft;
	 }
	  /**
	   * returns a string of number of cards left and the values of cards in the deck
	   */
	public String toString(){
		String rtrnStrng = new String(getCardsLeft()+"\n");
		for (int x = 0;x<getCardsLeft();x++){
			rtrnStrng = rtrnStrng + deck[x].toString()+"\n";
		}
		return rtrnStrng;
	}
}
	 

	
	
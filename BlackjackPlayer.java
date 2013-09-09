/**
 * 
 * @author Montek
 *
 */
public class BlackjackPlayer {
	
	private Card[] hand = new Card[11];
	private String playerName;
	private int numCards = 0;
	private int numAces = 0;
	/**
	 * Constructor gives player a name
	 * @param name
	 */
	public BlackjackPlayer(String name){
		playerName = name;
	}
	/**
	 * add card objects to the hand array
	 * @param card1 card object for first card dealt
	 * @param card2 second card dealt
	 */
	public void newHand(Card card1, Card card2){
		hand[0] = card1;
		hand[1] = card2;
		if(card1.getFace().equals("Ace")){
			numAces++;
			
		}
		if(card2.getFace().equals("Ace")){
			numAces++;
		}
		
		numCards+=2;
	}
	/**
	 * adds a card to the hand
	 * @param card card dealt by dealer
	 */
	public void addCard(Card card){
		hand[numCards] = card;
		numCards++;
		if(card.getFace().equals("Ace")){
			numAces++;
		}
	}
	/**
	 * makes all values in hand array null
	 */
	public void clearHand(){
		for (int x=0; x<11;x++){
			hand[x] = null;
		}
		numCards=0;
	}
	/**
	 * reads the face value of card and turns it into an integer
	 * @param card takes in card that needs to be evaluated
	 * @return returns an integer representing card value
	 */
	public static int evaluateCard(Card card){
		int rVal = 0;
		switch(card.getFace()){
		case "Ace": rVal=11; break;
		case "2": rVal=2;break;
		case "3": rVal=3;break;
		case "4": rVal=4;break;
		case "5": rVal=5;break;
		case "6": rVal=6;break;
		case "7": rVal=7;break;
		case "8": rVal=8;break;
		case "9": rVal=9;break;
		case "10": rVal=10;break;
		case "Jack": rVal=10;break;
		case "Queen": rVal=10;break;
		case "King": rVal=10;break;
		}
		return rVal;
	}
	/**
	 * calculates all the possible combinations of the cards in hand
	 * @return array consisting of the possible combinations
	 */ 
	
	public int[] handValues(){
		 int value=0;
         int numAces=0;
         for (int i=0; i<numCards; i++)
         {
           value+=evaluateCard(hand[i]);
           if(hand[i].getFace().equals("Ace"))
        	   numAces++;
         }
         //System.out.println("Aces" + numAces + " numCards" +numCards);

         int [] handValues =new int [numAces+1];
         handValues[0]=value;
         for (int i=0; i<numAces; i++)
         {
        	 value+=10; //adds 10 for each Ace, since Ace was originally set as 1. 
             handValues[i]=value; 
         }
         return handValues;
		
	}
		
	/**
	 * 
	 * @return returns number of cards in hand
	 */
		
	public int getNumCards(){
		return numCards;
	}
	/**
	 * finds best score that is greatest but not exceeding 21
	 * if bust, then returns -1
	 * @return integer of max
	 */

		public int bestScore()
		{
			int max = handValues()[0];
			if (bust()){
				return -1;
			}
			for (int x = 0; x<handValues().length; x++){
				if (handValues()[x]>max){
					max = handValues()[x];
				}
			}
			return max;
		}
		/**
		 * checks whether the player bust or not.
		 * @return boolean value if bust or not
		 */
		public boolean bust()
		{
			
			for(int x=0;x<handValues().length;x++){
				if (handValues()[x]<=21){
					return false;
				}
			}
			return true;
		}
		/**
		 * 
		 * @return player name
		 */
		public String getPlayerName(){
			return playerName;
		}
		/**
		 * returns string of values
		 */
		public String toString()
		{
			String st = getPlayerName();
			for (int i=0;i<handValues().length;i++){
				st+=": "+handValues()[i];
			}
			for (int i=0;i<numCards;i++){
				st+=": "+hand[i].toString();
			}
			return st;
		}

		public static void main(String[] args)
		{
			/* I suggest you test these incrementally - don't try to run
			 * all the tests at once, but comment out the rest and then
			 * add them one at a time
			 */
			
			BlackjackPlayer p = new BlackjackPlayer("Burdell");
			DeckOfCards d = new DeckOfCards();
			System.out.println(d.toString());
			//p.newHand(d.deal(), d.deal());
			Card c1 = new Card("Ace", "Hearts");
			Card c2= new Card("2", "Hearts");
			p.newHand(c1, c2);

			/*May want to manually check hand first if having problems*/
			
			//System.out.println("Check hand by individual cards (Should print two cards)");
			//for (int i = 0; i < p.getNumCards(); i++)
			//{
			//	System.out.println(p.hand[i]);
			//}
			//System.out.println();

			System.out.println(p);
			System.out.println("Bust? "+p.bust()+"\n");
			System.out.println(p.handValues()[0]);

			System.out.println("Testing Aces: ");
			p.addCard(new Card("Ace", "Spades"));
			System.out.println(p+"\n");
			//System.out.println(p.handValues()[0]+" "+p.handValues()[1]);

			System.out.println("Testing Aces: ");
			p.addCard(new Card("Ace", "Clubs"));
			System.out.println(p+"\n");
			//System.out.println(p.handValues()[0]+" "+p.handValues()[1]+" "+p.handValues()[2]+" "+p.handValues()[3]);


			System.out.println("Adding cards to guarantee bust...");
			p.addCard(new Card("Jack", "Hearts"));
			System.out.println(p+"\n");
			System.out.println("Bust? "+p.bust()); //Probably wont bust here yet
			//System.out.println(p.handValues()[0]+" "+p.handValues()[1]+" "+p.handValues()[2]+" "+p.handValues()[3]);
			p.addCard(new Card("Jack", "Spades"));

			System.out.println(p+"\n");
			System.out.println("Bust? "+p.bust());
			if (p.bust() == false)
				System.out.println("Something is wrong - Didn't bust!");
			//	System.out.println(p.handValues()[0]+" "+p.handValues()[1]+" "+p.handValues()[2]+" "+p.handValues()[3]);
			

			System.out.println("Testing clear (no cards should print: ");
			p.clearHand();
			//System.out.println(p+"\n");

			/*TODO: Now write your own test case to make sure 
			 * you can add can create a new hand.
			 *
			 * You may also want to make additional test cases
			 * to make sure all your classes work optimally,
			 * as we will also be conducted additional tests
			 */
			System.out.println("Testing new hand creation: ");
			//TODO
		}
	}

	
	
	


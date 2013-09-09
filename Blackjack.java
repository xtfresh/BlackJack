import java.util.Scanner;

/**
 * This is a game driver for your Blackjack classes. If you provided
 * all the methods in the homework assignment, this should work without
 * issue. I have not had time to clean up or comment this code,
 * nor can I promise I will before Thursday.
 *
 * If your classes do not work with this driver, that does not *necessarily*
 * mean there is something wrong with your code - I just threw this
 * together yesterday and have not tested it yet.
 *
 * @author Sundeep Ghuman
 * @version 1.0 beta
 */
public class Blackjack
{
	private BlackjackPlayer player1, computer;
	private String dealersHand;
	private Card visibleCard;
	private DeckOfCards deck;
	private static Scanner scan = new Scanner(System.in);

	public Blackjack(String playerName)
	{
		deck = new DeckOfCards();
		deck.shuffle();

		computer = new BlackjackPlayer("Dealer");
		Card visible = deck.deal();
		computer.newHand(visible, deck.deal());
		dealersHand = visible + ", *";

		player1 = new BlackjackPlayer(playerName);
		player1.newHand(deck.deal(), deck.deal());
	}

	/**
	 * Resets all player's hands to start a new round
	 */
	public void newRound()
	{
		player1.clearHand();
		computer.clearHand();
		player1.newHand(deck.deal(), deck.deal());
		Card visible = deck.deal();
		computer.newHand(visible, deck.deal());
		dealersHand = visible + ", *";
	}

	public void printGameStatus()
	{
		System.out.println("Dealers Hand: "+dealersHand);
		System.out.println(player1); //print Player's hand
	}

	/*
	 * Makes a move for a player. If the player chose hit rather than stay,
	 * it will return true indicating that the player will continue
	 * playing. However, if the hit results in a bust, it will
	 * return false since it is not possible to move again.
	 *
	 * @return true if the play can move again, false otherwise
	 */
	public boolean playerMove()
	{
		printGameStatus();
		String move = promptLine("Hit? (y/n/hit/stay)");
		if (move.equalsIgnoreCase("n") || move.equalsIgnoreCase("no")
				|| move.equalsIgnoreCase("stay"))
			return false;
		else if (move.equalsIgnoreCase("y") || move.equalsIgnoreCase("yes")
				|| move.equalsIgnoreCase("hit"))
		{
			hit();
			if (player1.bust())
				return false;
			else 
				return true;
			
		}
		else
		{
			System.out.println("Not a valid move - try again");
			return playerMove(); //Try again
		}
	}

	/*
	 * Dealers turn to play
	 *
	 * @return true if the dealer wins
	 */
	public boolean dealersTurn()
	{
		int playerScore = player1.bestScore();
		if (player1.bust()) //redundancy
			return true;
		if (computer.bust())
			return true;
		
		int computerScore = computer.bestScore();
		System.out.println(player1.getPlayerName()+"'s score: "+playerScore);
		System.out.println(computer);
		while (playerScore > computerScore)
		{
			promptLine("Dealer's turn - Hit enter to continue");
			System.out.println("\nDealer Hits");
			computer.addCard(deck.deal());
			computerScore = computer.bestScore();	
			System.out.println(computer);
			if (computer.bust())
			{
				System.out.println("Dealer Busted!");
				return false;
			}
		}
		
		//if loop completes (computer did not bust)
		//dealer has won - dealer wins ties
		return true;

	}

	public void hit()
	{
		player1.addCard(deck.deal());
	}

	/*
	 * Method to reuse prompt code - why must it be static?
	 * Will automatically insert a space at the end of the prompt
	 *
	 * @param prompt THe prompt for input to print
	 * @return The String input that user entered in the console
	 */
	public static String promptLine(String prompt)
	{
		System.out.print(prompt+" ");
		return scan.nextLine();
	}

	public static void main(String[] args)
	{
		String player = promptLine("Enter your name:");
		Blackjack game = new Blackjack(player);
		//game.printGameStatus(); //at this point I ran the program
					//to check that it worked
		boolean keepPlaying = true;

		while (keepPlaying)
		{
			boolean playerContinue;
			do
			{
				playerContinue = game.playerMove();
			} while (playerContinue); //Loops until player cannot move again
	
			//Player's turn is done - now determine outcome
			//If bust, lose, else attempt for dealer to get
	
			if (game.player1.bust())
			{
				System.out.println(player+" Busted - Loser!\nDealer Wins");
				
			}
			else
			{
				boolean dealerWin = game.dealersTurn();
				System.out.println(player+"'s score: "+game.player1.bestScore());
				if (dealerWin)
					System.out.println("Dealer Wins");
				else //Dealer plays until win or bust
					System.out.println(game.player1.getPlayerName()+" Wins!");
			}
			if (game.deck.getCardsLeft() < 10) //rough hack to make sure always enough cards to keep playing - will not always work
				keepPlaying = false;
			else
			{
				String playAgain = promptLine("\nPlay another Round? (yes/y to continue):");
				if (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y"))
				{
					keepPlaying = true;
					game.newRound();
				}
				else
					keepPlaying = false;

			}
		}
	}
}

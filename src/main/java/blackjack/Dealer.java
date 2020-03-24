package blackjack;

import blackjack.exceptions.BustException;
import blackjack.exceptions.DeckEmptyException;

public class Dealer
{
    private Deck deck = new Deck();
    private Hand hand = new Hand(deck);
    private Player[] players;
    private final int betMultiplier = 2;

    public Dealer()
    {
        getPlayers();

        showSingleCard(0);

        for (Player player : players) playRound(player);

        showHand();

        boolean bust = dealerPlay();

        if (bust == true)
        {
            System.out.println("Lets check who won");
            for (Player player : players) checkPlayerWin(player);
        }
    }

    public void getPlayers()
    {
        int numPlayers = CLI.getInt("How many players are there: ");

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++)
        {
            String name = CLI.getString("What is player " + (i+1) + "'s name: ");

            Hand playerHand = new Hand(deck);

            players[i] = new Player(playerHand, name, i);
        }
    }

    public void checkPlayerWin(Player player)
    {
        System.out.println("Okay " + player.getName());

        if (player.getBust() != true)
        {
            if (player.getHand().getTotalValue() > hand.getTotalValue())
            {
                System.out.println("You beat me!");
            }
            else if (player.getHand().getTotalValue() == hand.getTotalValue())
            {
                System.out.println("We drew!");
            } 
            else
            {
                System.out.println("I beat you");
            }
        }
    }

    public boolean dealerPlay()
    {
        while (hand.getTotalValue() <= 16)
        {
            CLI.wait(1);
            System.out.println("Hit!");
            try
            {
                Card newCard = deck.pickCard();

                System.out.println("Card: " + newCard.getName());

                hand.addCard(newCard);

                System.out.println("New hand value: " + hand.getTotalValue());
            } catch (BustException e)
            {
                System.out.println("I've gone bust with a hand value of: " + hand.getTotalValue());

                return false;
            } catch (DeckEmptyException e) { System.out.println("Deck empty"); }
        }

        System.out.println("That's enough");
        System.out.println("My ending hand value: " + hand.getTotalValue());

        return true;
    }

    public void showSingleCard(int index)
    {
        System.out.println("My first card is " + hand.getCard(index).getName());
    }

    public void showHand()
    {
        System.out.println("My hand is");
        CLI.printHand(hand);
    }

    public void playRound(Player player)
    {
        System.out.println("Okay " + player.getName());

        Hand playersHand = player.getHand();

        System.out.println("Your hand is:");
        CLI.printHand(playersHand);

        String choice = "";
        while (!choice.equals("s") && player.getBust() == false)
        {
            choice = CLI.getString("Hit (H) or Stick (S): ").toLowerCase();

            if (choice.equals("h"))
            {
                try
                {
                    Card newCard = deck.pickCard();

                    System.out.println("The new card is " + newCard.getName());

                    player.hit(newCard);

                    if (player.getBust() == true) 
                    {
                        System.out.println("Gone bust with a total hand value of " + playersHand.getTotalValue() + "\n");
                    }
                    else System.out.println("New value: " + playersHand.getTotalValue());
                } catch (DeckEmptyException e) { System.out.println("Deck empty"); }
            }
            else if (choice.equals("s"))
            {
                System.out.println("Okay, end value is " + playersHand.getTotalValue() + "\n");
            }
            else System.out.println("Option not recognised");
        }
    }
}
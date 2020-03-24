package blackjack;

import blackjack.exceptions.DeckEmptyException;

public class Dealer
{
    private Deck deck = new Deck();
    private Hand hand = new Hand(deck);
    private Player[] players;

    public Dealer()
    {
        getPlayers();

        showSingleCard();

        for (Player player : players) playRound(player);
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

    public void showSingleCard()
    {
        System.out.println("My first card is " + hand.getCard(0).getName());
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
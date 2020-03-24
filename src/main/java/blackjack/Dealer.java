package blackjack;

public class Dealer
{
    private Deck deck = new Deck();
    private Hand hand = new Hand(deck);
    private Player[] players;

    public Dealer()
    {
        getPlayers();

        showSingleCard();
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
}
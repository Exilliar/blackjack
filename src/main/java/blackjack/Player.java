package blackjack;

public class Player
{
    private Hand hand;
    private String name;
    private int id;

    public Player(Hand h, String n, int i)
    {
        hand = h;
        name = n;
        id = i;
    }

    public Hand getHand() { return hand; }
    public String getName() { return name; }
    public int getId() { return id; }
}
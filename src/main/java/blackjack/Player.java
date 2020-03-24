package blackjack;

import blackjack.exceptions.BustException;

public class Player
{
    private Hand hand;
    private String name;
    private int id;
    private boolean bust = false;

    public Player(Hand h, String n, int i)
    {
        hand = h;
        name = n;
        id = i;
    }

    public void hit(Card card)
    {
        try
        {
            hand.addCard(card);
        }
        catch(BustException e) { bust = true; }
    }

    public Hand getHand() { return hand; }
    public String getName() { return name; }
    public int getId() { return id; }
    public boolean getBust() { return bust; }
}
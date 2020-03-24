package blackjack;

import blackjack.exceptions.BustException;
import blackjack.exceptions.InsufficientMoneyException;

public class Player
{
    private Hand hand;
    private String name;
    private int id;
    private boolean bust = false;
    private int money = 1000;
    private int bet;

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

    public void bet(int amount)
    throws InsufficientMoneyException
    {
        if (money >= amount) 
        {
            money -= amount;
            bet = amount;
        }
        else throw new InsufficientMoneyException();
    }

    public void win(int multiplier)
    {
        money += bet*multiplier;

        bet = 0;
    }

    public Hand getHand() { return hand; }
    public String getName() { return name; }
    public int getId() { return id; }
    public boolean getBust() { return bust; }
    public int getMoney() { return money; }
    public int getBet() { return bet; }
}
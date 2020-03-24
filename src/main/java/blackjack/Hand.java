package blackjack;

import java.util.ArrayList;

import blackjack.exceptions.BustException;

public class Hand
{
    private ArrayList<Card> cards = new ArrayList<Card>(2);
    private int totalValue;

    public Hand(Card card0, Card card1)
    {
        cards.add(card0);
        cards.add(card1);

        totalValue = card0.getValue() + card1.getValue();
    }

    public void addCard(Card card)
    throws BustException
    {
        cards.add(card);

        totalValue += card.getValue();

        if (totalValue > 21) throw new BustException();
    }

    public int getTotalValue() { return totalValue; }
}
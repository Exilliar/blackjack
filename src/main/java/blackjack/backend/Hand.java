package blackjack.backend;

import java.util.ArrayList;

import blackjack.exceptions.BustException;
import blackjack.exceptions.DeckEmptyException;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<Card>(2);
    private int totalValue;

    public Hand(Card card0, Card card1) {
        cards.add(card0);
        cards.add(card1);

        totalValue = card0.getValue() + card1.getValue();
    }

    public Hand(Deck deck) {
        try {
            Card card0 = deck.pickCard();
            Card card1 = deck.pickCard();

            cards.add(card0);
            cards.add(card1);

            totalValue = card0.getValue() + card1.getValue();
        } catch (DeckEmptyException e) {
            System.out.println("");
        }
    }

    public void addCard(Card card) throws BustException {
        cards.add(card);

        totalValue += card.getValue();

        if (totalValue > 21) {
            // Check for aces and lower the value if needed, then recheck the totalvalue
            for (Card c : cards) {
                if (c instanceof Ace) {
                    ((Ace) c).changeVal();
                    totalValue -= 10;
                }
            }
            if (totalValue > 21)
                throw new BustException();
        }
    }

    public int getTotalValue() {
        return totalValue;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }
}
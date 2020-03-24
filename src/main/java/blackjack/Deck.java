package blackjack;

import java.util.ArrayList;
import java.util.Random;

import blackjack.exceptions.DeckEmptyException;

public class Deck
{
    ArrayList<Card> cards = new ArrayList<Card>(52);

    public Deck()
    {
        // Create deck
        // Hearts
        createSuit("H");

        // Diamonds
        createSuit("D");

        // Clubs
        createSuit("C");

        // Spades
        createSuit("S");
    }

    public Card pickCard()
    throws DeckEmptyException
    {
        if (cards.size() > 0)
        {
            Random rand = new Random();

            int currentSize = cards.size();

            int randIndex = rand.nextInt(currentSize);

            Card randCard = cards.get(randIndex);

            cards.remove(randIndex);

            return randCard;
        }
        else
        {
            throw new DeckEmptyException();
        }
    }

    public ArrayList<Card> getCards() { return cards; }

    private void createSuit(String suitName)
    {
        // Number cards
        for (int i = 1; i <= 10; i++) cards.add(new Card(Integer.toString(i) + suitName,i));

        // Picture cards
        cards.add(new Card("J" + suitName, 10));
        cards.add(new Card("Q" + suitName, 10));
        cards.add(new Card("K" + suitName, 10));
        cards.add(new Card("A" + suitName, 11)); // Will likely need to create new class with overrides to handle spaces
    }
}
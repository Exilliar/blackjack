package blackjack.backend;

import java.util.ArrayList;
import java.util.Random;

import blackjack.exceptions.DeckEmptyException;

public class Deck
{
    private int startingDeckSize;
    private int singleDeckSize = 52;
    private ArrayList<Card> cards = new ArrayList<Card>(52);

    public Deck()
    {
        startingDeckSize = singleDeckSize;

        createDeck();
    }

    public Deck(int numDecks)
    {
        startingDeckSize = singleDeckSize*numDecks;

        cards = new ArrayList<Card>(startingDeckSize);

        for (int i = 0; i < numDecks; i++)
        {
            createDeck();
        }
    }

    public void resetDeck()
    {
        cards = new ArrayList<Card>(startingDeckSize);

        createDeck();
    }

    private void createDeck()
    {
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
        else throw new DeckEmptyException();
    }

    private void createSuit(String suitName)
    {
        // Number cards
        for (int i = 2; i <= 10; i++) cards.add(new Card(Integer.toString(i) + suitName,i));

        // Picture cards
        cards.add(new Card("J" + suitName, 10));
        cards.add(new Card("Q" + suitName, 10));
        cards.add(new Card("K" + suitName, 10));
        cards.add(new Ace(suitName));
    }

    public ArrayList<Card> getCards() { return cards; }
    public int getSingleDeckSize() { return singleDeckSize; }
    public int getStartingDeckSize() { return startingDeckSize; }
    public int getDeckSize() { return cards.size(); }
}
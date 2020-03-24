package blackjack;

import java.util.ArrayList;

import org.junit.Test;

import blackjack.exceptions.DeckEmptyException;

import static org.junit.Assert.*;

public class DeckTest 
{
    Deck deck = new Deck();
    @Test
    public void testConstructor()
    {
        ArrayList<Card> expectedDeck = addCards();
        ArrayList<Card> actualDeck = deck.getCards();

        for (int i = 0; i < expectedDeck.size(); i++)
        {
            Card expectedCard = expectedDeck.get(i);
            Card actualCard = actualDeck.get(i);

            assertEquals(expectedCard.getName(), actualCard.getName());
            assertEquals(expectedCard.getValue(), actualCard.getValue());
        }
    }

    // Test size of cards is decreased by 1
    @Test
    public void testPickCardDecrease()
    {
        int startingSize = deck.getCards().size();

        try {
            deck.pickCard();
        } catch(DeckEmptyException e){ assert(false); }

        assertTrue("Deck size should have decreased by 1", deck.getCards().size() == startingSize-1);
    }

    // Test the card is actually removed
    @Test
    public void testPickCardRemoved()
    {
        try 
        {
            Card pickedCard = deck.pickCard();

            deck.getCards().forEach(d -> {
                assertNotEquals(d.getName(), pickedCard.getName()); // Only checking names as values will repeat
            });
        } catch(DeckEmptyException e) { assert(false); }
    }

    // Test that it throws the correct error when there are no cards left
    // TODO make work
    @Test
    public void testErrorThrow()
    {
        try
        {
            for (int i = 0; i < 52; i++) deck.pickCard();
        } catch(DeckEmptyException e) { assert(false); }

        try
        {
            deck.pickCard();
        } 
        catch(DeckEmptyException e) { assert(true); }
        catch(Exception e) { assert(false); }
    }

    private ArrayList<Card> addCards()
    {
        ArrayList<Card> d = new ArrayList<Card>(52);
        // Hearts
        d.add(new Card("1H", 1));
        d.add(new Card("2H", 2));
        d.add(new Card("3H", 3));
        d.add(new Card("4H", 4));
        d.add(new Card("5H", 5));
        d.add(new Card("6H", 6));
        d.add(new Card("7H", 7));
        d.add(new Card("8H", 8));
        d.add(new Card("9H", 9));
        d.add(new Card("10H", 10));

        d.add(new Card("JH", 10));
        d.add(new Card("QH", 10));
        d.add(new Card("KH", 10));
        d.add(new Card("AH", 11));

        // Diamonds
        d.add(new Card("1D", 1));
        d.add(new Card("2D", 2));
        d.add(new Card("3D", 3));
        d.add(new Card("4D", 4));
        d.add(new Card("5D", 5));
        d.add(new Card("6D", 6));
        d.add(new Card("7D", 7));
        d.add(new Card("8D", 8));
        d.add(new Card("9D", 9));
        d.add(new Card("10D", 10));

        d.add(new Card("JD", 10));
        d.add(new Card("QD", 10));
        d.add(new Card("KD", 10));
        d.add(new Card("AD", 11));

        // Clubs
        d.add(new Card("1C", 1));
        d.add(new Card("2C", 2));
        d.add(new Card("3C", 3));
        d.add(new Card("4C", 4));
        d.add(new Card("5C", 5));
        d.add(new Card("6C", 6));
        d.add(new Card("7C", 7));
        d.add(new Card("8C", 8));
        d.add(new Card("9C", 9));
        d.add(new Card("10C", 10));

        d.add(new Card("JC", 10));
        d.add(new Card("QC", 10));
        d.add(new Card("KC", 10));
        d.add(new Card("AC", 11));

        // Spades
        d.add(new Card("1S", 1));
        d.add(new Card("2S", 2));
        d.add(new Card("3S", 3));
        d.add(new Card("4S", 4));
        d.add(new Card("5S", 5));
        d.add(new Card("6S", 6));
        d.add(new Card("7S", 7));
        d.add(new Card("8S", 8));
        d.add(new Card("9S", 9));
        d.add(new Card("10S", 10));

        d.add(new Card("JS", 10));
        d.add(new Card("QS", 10));
        d.add(new Card("KS", 10));
        d.add(new Card("AS", 11));

        return d;
    }
}
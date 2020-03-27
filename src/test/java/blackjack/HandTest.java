package blackjack;

import static org.junit.Assert.*;
import org.junit.Test;

import blackjack.exceptions.BustException;

public class HandTest
{
    Hand hand = new Hand(new Card("1H", 1), new Card("KD", 10));

    @Test
    public void testConstructor()
    {
        assertEquals(11, hand.getTotalValue());
    }

    @Test
    public void testAddCard()
    {
        try {
            hand.addCard(new Card("KH", 10));

            assertEquals(21, hand.getTotalValue());
        } catch(BustException e) { assert(false); }
    }

    // TODO test exception


    @Test 
    public void testAce()
    {
        try {
            hand.addCard(new Ace("H"));

            assertEquals(12, hand.getTotalValue());
        } catch(BustException e) { assert(false); }
    }
}
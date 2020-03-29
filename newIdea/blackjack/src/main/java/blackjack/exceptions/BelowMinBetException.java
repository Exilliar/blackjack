package blackjack.exceptions;

public class BelowMinBetException extends Exception
{
    public BelowMinBetException()
    {
        super("Bet below min bet");
    }
}
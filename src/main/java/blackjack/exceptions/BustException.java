package blackjack.exceptions;

public class BustException extends Exception {
    public BustException() {
        super("Hand over 21");
    }
}
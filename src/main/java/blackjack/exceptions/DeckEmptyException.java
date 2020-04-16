package blackjack.exceptions;

public class DeckEmptyException extends Exception {
    public DeckEmptyException() {
        super("Deck is empty");
    }
}
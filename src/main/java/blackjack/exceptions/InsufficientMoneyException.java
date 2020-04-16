package blackjack.exceptions;

public class InsufficientMoneyException extends Exception {
    public InsufficientMoneyException() {
        super("Not enough money");
    }
}
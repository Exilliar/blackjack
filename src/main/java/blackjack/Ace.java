package blackjack;

public class Ace extends Card
{
    private int secondValue = 1;

    public Ace(String suit) { super("A" + suit, 11); }

    public void changeVal() { super.setValue(secondValue); }
}
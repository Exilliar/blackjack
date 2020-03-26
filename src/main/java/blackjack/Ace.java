package blackjack;

public class Ace extends Card
{
    public Ace(String suit) { super("A" + suit, 11); }

    public void changeVal() { super.setValue(1); }
}
package blackjack;

public class Card
{
    private String name;
    private int value;

    public Card(String n, int v)
    {
        name = n;
        value = v;
    }

    public String getName() { return name; }
    public int getValue() { return value; }
}
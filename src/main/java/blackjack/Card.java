package blackjack;

public class Card
{
    String name;
    int value;

    public Card(String n, int v)
    {
        name = n;
        value = v;
    }

    public String getName() { return name; }
    public int getValue() { return value; }
}
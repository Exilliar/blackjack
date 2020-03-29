package blackjack.backend;

public class Card
{
    private String name;
    private int value;

    public Card(String n, int v)
    {
        name = n;
        value = v;
    }

    public void setValue(int v) { value = v; } // This will likely only be used for the ace child class

    public String getName() { return name; }
    public int getValue() { return value; }
}
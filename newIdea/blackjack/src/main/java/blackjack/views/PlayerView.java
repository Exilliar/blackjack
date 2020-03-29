package blackjack.views;

import java.util.ArrayList;

import blackjack.backend.Card;
import blackjack.backend.Player;

public class PlayerView
{
    private Player player;

    public PlayerView(Player p)
    {
        player = p;
    }

    public int getID() { return player.getId(); }
    public String getName() { return player.getName(); }
    public String[] getCardNames()
    {
        ArrayList<Card> cards = player.getHand().getCards();
        String[] names = new String[cards.size()];

        for (int i = 0; i < cards.size(); i++) { names[i] = cards.get(i).getName(); }

        return names;
    }
    public int[] getCardValues()
    {
        ArrayList<Card> cards = player.getHand().getCards();
        int[] values = new int[cards.size()];

        for (int i = 0; i < cards.size(); i++) { values[i] = cards.get(i).getValue(); }

        return values;
    }
    public int getTotalHandValue() { return player.getHand().getTotalValue(); }
    public int getMoney() { return player.getMoney(); }
    public int getBet() { return player.getBet(); }
    public boolean getPlayingRound() { return player.getPlayRound(); }
    public boolean getBust() { return player.getBust(); }
}
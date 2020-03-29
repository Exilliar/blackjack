package blackjack.backend;

import java.util.ArrayList;

import blackjack.exceptions.BelowMinBetException;
import blackjack.exceptions.BustException;
import blackjack.exceptions.DeckEmptyException;
import blackjack.exceptions.InsufficientMoneyException;
import blackjack.views.PlayerView;

public class Dealer {
    private ArrayList<Player> players;
    private Deck deck;
    private Hand hand;
    private int minBet = 50;
    private boolean bust = false;

    public Dealer() {
        deck = new Deck();
    }

    public PlayerView addPlayer(String name, int id) {
        Player newPlayer = new Player(name, id);

        players.add(newPlayer);

        return new PlayerView(newPlayer);
    }

    public void setBet(int bet, int playerId)
    throws InsufficientMoneyException, BelowMinBetException
    {
        if (bet < minBet) throw new BelowMinBetException();

        Player player = players.get(playerId);

        player.bet(bet);
    }

    public void deal()
    {
        hand = new Hand(deck);

        for (Player player : players)
        {
            player.setHand(new Hand(deck));
        }
    }

    public Card showFirstCard()
    {
        Card firstCard = hand.getCard(0);

        // A new card is created if the card is an Ace to stop the frontend being able to change the
        // value of the Ace using the changeVal() method. See /notes/PlayerView-class.md for more
        if (firstCard instanceof Ace) return new Card(firstCard.getName(),firstCard.getValue());
        else return firstCard;
    }

    public void dealerPlay()
    {
        while (hand.getTotalValue() < 16)
        {
            try {
                Card newCard = deck.pickCard();

                hand.addCard(newCard);
            } catch (DeckEmptyException e) {
                handleDeckEmptyException();
            } catch (BustException e) {
                bust = true;
            }
        }
    }

    public void playerHit(int playerId)
    throws BustException
    {
        Player player = players.get(playerId);

        try {
            Card newCard = deck.pickCard();

            player.hit(newCard);
        } catch (DeckEmptyException e) {
            handleDeckEmptyException();
        } catch (BustException e) {
            throw new BustException();
        }
    }

    public void findWinners()
    {
        if (bust == false) findWinnersBustFalse();
        else findWinnersBustTrue();
    }

    // The next 2 methods are needed as there are 2 different sets of logic required to find who won
    // depending on whether the dealer has gone bust
    private void findWinnersBustFalse()
    {

    }
    private void findWinnersBustTrue()
    {

    }

    public String[] getHandNames()
    {
        ArrayList<Card> cards = hand.getCards();
        String[] names = new String[cards.size()];

        for (int i = 0; i < cards.size(); i++) { names[i] = cards.get(i).getName(); }

        return names;
    }
    public int[] getHandValues()
    {
        ArrayList<Card> cards = hand.getCards();
        int[] values = new int[cards.size()];

        for (int i = 0; i < cards.size(); i++) { values[i] = cards.get(i).getValue(); }

        return values;
    }
    public boolean getBust(){ return bust; }

    private void handleDeckEmptyException()
    {
        System.out.println("Deck emtpy");
    }
}
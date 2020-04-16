package blackjack.backend;

import blackjack.exceptions.BustException;
import blackjack.exceptions.InsufficientMoneyException;

public class Player {
    private Hand hand;
    private String name;
    private int id;
    private boolean bust = false;
    private int money = 1000;
    private int bet;
    private boolean bankrupt = false;
    private boolean playRound = false;
    private boolean roundWin = false;
    private boolean roundDraw = false;

    public Player(String n, int i) {
        name = n;
        id = i;
    }

    public void hit(Card card) throws BustException {
        try {
            hand.addCard(card);
        } catch (BustException e) {
            bust = true;
            throw new BustException();
        }
    }

    public void bet(int amount) throws InsufficientMoneyException {
        if (money >= amount) {
            money -= amount;
            bet = amount;
        } else
            throw new InsufficientMoneyException();
    }

    public void win(int multiplier) {
        roundWin = true;

        money += bet * multiplier;

        bet = 0;
    }

    public void lose() {
        bet = 0;

        if (money == 0)
            bankrupt = true;
    }

    public void setBet(int b) {
        bet = b;
    }

    public void setMoney(int m) {
        money = m;
    }

    public void setPlayRound(boolean p) {
        playRound = p;
    }

    public void setHand(Hand h) {
        hand = h;
    }

    public void setRoundWin(boolean r) {
        roundWin = r;
    }

    public void setRoundDraw(boolean r) {
        roundDraw = r;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean getBust() {
        return bust;
    }

    public int getMoney() {
        return money;
    }

    public int getBet() {
        return bet;
    }

    public boolean getBankrupt() {
        return bankrupt;
    }

    public boolean getPlayRound() {
        return playRound;
    }

    public boolean getRoundWin() {
        return roundWin;
    }

    public boolean getRoundDraw() {
        return roundDraw;
    }
}
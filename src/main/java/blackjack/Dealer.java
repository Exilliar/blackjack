package blackjack;

import blackjack.exceptions.BustException;
import blackjack.exceptions.DeckEmptyException;
import blackjack.exceptions.InsufficientMoneyException;

public class Dealer
{
    private Deck deck = new Deck();
    private Hand hand = new Hand(deck);
    private Player[] players;
    private final int betMultiplier = 2;
    private final int minBet = 50;

    public Dealer()
    {
        getPlayers();

        CLI.wait(1);

        for (Player player : players) getBet(player);

        CLI.wait(1);

        showSingleCard(0);

        CLI.wait(1);

        for (Player player : players) playRound(player);

        CLI.wait(1);

        showHand();

        CLI.wait(1);

        boolean bust = dealerPlay();

        CLI.wait(1);

        System.out.println("Lets check who won");

        if (bust == true)
        {
            for (Player player : players) checkPlayerWin(player);
        }
        else
        {
            for (Player player : players)
            {
                if (player.getPlayRound() == true)
                {
                    if (player.getBust() == false)
                    {
                        System.out.println(player.getName() + " beat me!");
                        player.win(betMultiplier);
                        CLI.wait(1);
                    } else player.setBet(0);
                }
            }
        }

        for (Player player : players) System.out.println(player.getName() + ", your money is now at: " + player.getMoney());
    }

    public void getBet(Player player)
    {
        if (player.getBankrupt() == true) return;

        System.out.println("\nOkay " + player.getName() + "\nThe minimum bet is " + minBet);
        System.out.println("You have £" + player.getMoney());

        int bet = 0;

        boolean success = false;
        boolean noBet = false;
        
        while (success == false)
        {
            while (bet < minBet && noBet == false)
            {
                bet = CLI.getInt("How much do you want to bet (2:1 odds), if you do not want to bet then type -1: ");

                if (bet == -1)
                {
                    noBet = true;
                }
            }
            try {
                if (noBet == true)
                {
                    System.out.println("Okay " + player.getName() + " you've decided not to play this round");
                    success = true;
                    player.setPlayRound(false);
                }
                else
                {
                    player.bet(bet);
                    success = true;
                    player.setPlayRound(true);
                    System.out.println("Successful bet");
                }
            } catch (InsufficientMoneyException e) {
                System.out.println("You do not have that much money");

                player.setBet(0);
                player.setMoney(player.getMoney() + bet);
                bet = 0;
            }
        }

        CLI.wait(1);
    }

    public void getPlayers()
    {
        int numPlayers = CLI.getInt("How many players are there: ");

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++)
        {
            String name = CLI.getString("What is player " + (i+1) + "'s name: ");

            Hand playerHand = new Hand(deck);

            players[i] = new Player(playerHand, name, i);
        }
    }

    public void checkPlayerWin(Player player)
    {
        if (player.getPlayRound() == false) return;

        System.out.println("Okay " + player.getName());

        if (player.getBust() == true) 
        {
            System.out.println("I beat you!");
            return;
        }

        if (player.getHand().getTotalValue() > hand.getTotalValue())
        {
            System.out.println("You beat me!");

            player.win(betMultiplier);
        }
        else if (player.getHand().getTotalValue() == hand.getTotalValue())
        {
            System.out.println("We drew!");

            player.setMoney(player.getMoney() + player.getBet());

            player.setBet(0);
        } 
        else
        {
            System.out.println("I beat you");

            player.lose();
        }
    }

    public boolean dealerPlay()
    {
        while (hand.getTotalValue() <= 16)
        {
            CLI.wait(1);
            System.out.println("Hit!");
            try
            {
                Card newCard = deck.pickCard();

                System.out.println("Card: " + newCard.getName());

                hand.addCard(newCard);

                System.out.println("New hand value: " + hand.getTotalValue());
            } catch (BustException e)
            {
                System.out.println("I've gone bust with a hand value of: " + hand.getTotalValue());

                return false;
            } catch (DeckEmptyException e) { System.out.println("Deck empty"); }
        }

        System.out.println("That's enough");
        System.out.println("My ending hand value: " + hand.getTotalValue());

        return true;
    }

    public void showSingleCard(int index)
    {
        System.out.println("My first card is " + hand.getCard(index).getName());
    }

    public void showHand()
    {
        System.out.println("My hand is");
        CLI.printHand(hand);
    }

    public void playRound(Player player)
    {
        if (player.getPlayRound() == false) return;

        System.out.println("Okay " + player.getName());

        Hand playersHand = player.getHand();

        System.out.println("Your hand is:");
        CLI.printHand(playersHand);

        String choice = "";
        while (!choice.equals("s") && player.getBust() == false)
        {
            choice = CLI.getString("Hit (H) or Stick (S): ").toLowerCase();

            if (choice.equals("h"))
            {
                try
                {
                    Card newCard = deck.pickCard();

                    System.out.println("The new card is " + newCard.getName());

                    player.hit(newCard);

                    if (player.getBust() == true) 
                    {
                        System.out.println("Gone bust with a total hand value of " + playersHand.getTotalValue() + "\n");
                    }
                    else System.out.println("New value: " + playersHand.getTotalValue());
                } catch (DeckEmptyException e) { System.out.println("Deck empty"); }
            }
            else if (choice.equals("s"))
            {
                System.out.println("Okay, end value is " + playersHand.getTotalValue() + "\n");
            }
            else System.out.println("Option not recognised");
        }
    }
}
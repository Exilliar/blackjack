package blackjack;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import blackjack.backend.Card;
import blackjack.backend.Dealer;

import blackjack.exceptions.BelowMinBetException;
import blackjack.exceptions.BustException;
import blackjack.exceptions.InsufficientMoneyException;

import blackjack.views.PlayerView;

public final class CLI
{
    static Dealer dealer = new Dealer();
    static ArrayList<PlayerView> playerViews;

    public static void main(String[] args)
    {
        int numPlayers = getInt("How many people are playing: ");

        wait(1);

        playerViews = getPlayers(numPlayers);

        wait(1);

        System.out.println("\nBetting time!\nThe minimum bet is " + dealer.getMinBet());

        wait(1);

        getBets();

        wait(1);

        dealHands();

        wait(1);

        playRound();
    }

    public static ArrayList<PlayerView> getPlayers(int numPlayers)
    {
        ArrayList<PlayerView> players = new ArrayList<PlayerView>(numPlayers);

        for (int i = 0; i < numPlayers; i++)
        {
            String name = getString("What is player " + (i+1) + "'s name: ");

            players.add(dealer.addPlayer(name, i));

            wait(1);
        }

        return players;
    }

    public static void getBets()
    {
        for (PlayerView player : playerViews)
        {
            System.out.println("\nOkay " + player.getName());

            boolean successfulBet = false;

            while (!successfulBet)
            {
                int bet = getInt("How much do you want to bet (-1 to skip this round): ");

                if (bet != -1)
                {
                    try {
                        dealer.setBet(bet, player.getID());

                        successfulBet = true;
                    } catch (InsufficientMoneyException e) {
                        System.out.println("You do not have that much money!");
                    } catch (BelowMinBetException e) {
                        System.out.println("That's not high enough!");
                    }
                } else successfulBet = true;
            }
        }
    }

    public static void dealHands()
    {
        dealer.deal();

        Card firstCard = dealer.getFirstCard();

        System.out.println("\nThe dealers first card is " + firstCard.getName());
        System.out.println("Giving their hand a current value of " + firstCard.getValue());
    }

    public static void playRound()
    {
        for (PlayerView player : playerViews)
        {
            System.out.println("\nOkay " + player.getName());

            System.out.println("You hand is");

            printHand(player);

            String option = "";

            boolean bust = false;

            while (!option.equals("s") && bust == false)
            {
                option = getString("Do you want to hit(h) or stick(s): ").toLowerCase();

                boolean validOption = false;

                while (validOption != true)
                {
                    if (option.equals("h") || option.equals("s"))
                    {
                        validOption = true;
                        if (option.equals("h"))
                        {
                            try {
                                dealer.playerHit(player.getID());

                                System.out.println("New card " + player.getLastCardName());

                                System.out.println("Your new hand is");

                                printHand(player);
                            } catch (BustException e) {
                                System.out.println("New card " + player.getLastCardName());

                                wait(1);

                                System.out.println("You've gone bust!");

                                bust = true;
                            }
                        }
                    } else System.out.println("Not an option");
                }
            }

            if (bust == false)
            {
                System.out.println("\nYour final hand is");

                printHand(player);
            }

            wait(1);
        }
    }

    private static void printHand(PlayerView player)
    {
        String[] cardNames = player.getCardNames();

        for (String name : cardNames)
        {
            System.out.println(name);
        }

        System.out.println("Giving your hand a total value of " + player.getTotalHandValue());
    }

    private static int getInt(String msg)
    {
        Scanner s = new Scanner(System.in);

        System.out.print(msg);

        return s.nextInt();
    }

    private static String getString(String msg)
    {
        Scanner s = new Scanner(System.in);

        System.out.print(msg);

        return s.nextLine();
    }

    public static void wait(int seconds)
    {
        try
        {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) { System.out.println("that's unexpected"); }
    }
}

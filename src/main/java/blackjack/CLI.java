package blackjack;

import java.util.Scanner;

public final class CLI 
{
    public static void main(String[] args) 
    {
        Deck deck = new Deck();

        int numplayers = getInt("How many players: ");
    }

    public static int getInt(String message)
    {
        Scanner s = new Scanner(System.in);

        System.out.print(message);

        return s.nextInt();
    }
}

package blackjack;

import java.util.Scanner;

public final class CLI 
{
    public static void main(String[] args) 
    {
        Dealer dealer = new Dealer();
    }

    public static int getInt(String message)
    {
        Scanner s = new Scanner(System.in);

        System.out.print(message);

        return s.nextInt();
    }

    public static String getString(String message)
    {
        Scanner s = new Scanner(System.in);

        System.out.print(message);

        return s.nextLine();
    }
}

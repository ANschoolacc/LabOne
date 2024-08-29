package se.labOne.java;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {

    ArrayList<Price> priceList = new ArrayList<>();
    String userChoice;

    userChoice = menu();

    if (userChoice.equals("e") || userChoice.equals("E")) {
      System.exit(0);
    }

    while (!userChoice.equals("e")) {
      if (userChoice.equals("1")) {
        priceList = Price.generateTimes(priceList);
        priceList = Price.setPrices(priceList);
        userChoice = menu();
      } else if (userChoice.equals("2")) {
        Price.minMaxAverage(priceList);
        userChoice = menu();
      } else if (userChoice.equals("3")) {
        Price.sortByPrice(priceList);
        userChoice = menu();
      } else if (userChoice.equals("4")) {
        Price.lowestWindow(priceList);
        userChoice = menu();
      }
    }

  }

  public static String menu() {

    System.out.println("Elpriser");
    System.out.println("========");
    System.out.println("1. Inmatning");
    System.out.println("2. Min, Max, Medel");
    System.out.println("3. Sortera");
    System.out.println("4. Bästa Laddningstid (4h)");
    System.out.println("e. Avsluta");

    String userChoice;
    while (true) {
      try {
        userChoice = sc.nextLine();
        if (userChoice.length() == 1 &&
            (('1' <= userChoice.charAt(0) && userChoice.charAt(0) <= '4')
                || (userChoice.charAt(0) == 'e' || userChoice.charAt(0) == 'E'))) {
          return userChoice;
        } else {
          throw new InputMismatchException();
        }
      } catch (InputMismatchException e) {
        System.out.println("Ogiltig inmatning");

      }
    }
  }

}




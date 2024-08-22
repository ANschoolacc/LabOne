package se.labOne.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    String userChoice;

    userChoice = menu();

    System.out.println(userChoice);

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
        if (userChoice.length() == 1 && (('1' <= userChoice.charAt(0) && userChoice.charAt(0) <= '4') || (userChoice.charAt(0) == 'e' || userChoice.charAt(0) == 'E'))) {
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

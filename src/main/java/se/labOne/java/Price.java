package se.labOne.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Price {
  String time;
  int price;

  Price(String time, int price) {
    this.time = time;
    this.price = price;
  }

  public static Price[] generateTimes() {

    Price[] prices;

    prices = new Price[24];

    int startTime = 0;
    int endTime = 1;
    for (int i = 0; i < prices.length; i++) {
      if (startTime < 10 && endTime < 10) {
        prices[i] = new Price("0" + startTime + "-" + "0" + endTime, 0);
      } else if (startTime < 10) {
        prices[i] = new Price("0" + startTime + "-" + endTime, 0);
      }else {
        prices[i] = new Price(startTime + "-" + endTime, 0);
      }
      startTime++;
      endTime++;
    }

    return prices;
  }

  public static Price[] setPrices(Price[] prices) {

    for (Price price : prices) {
      System.out.println("Vänligen mata in pris (I hela ören) för den angivna tiden");
      System.out.println(price.time);
      price.price = readInt(Main.sc);
    }

    for (int i = 0; i < prices.length; i++) {
      System.out.println(prices[i].time + " " + prices[i].price);
    }
    return prices;
  }

  public static int readInt(Scanner scanner) {
    int userPrice = 0;
    while (true) {
      try {
        userPrice = Main.sc.nextInt();
        if (userPrice < 0) {
          throw new InputMismatchException();
        } else {
          return userPrice;
        }
      } catch (InputMismatchException e) {
        System.out.println("Värdet måste vara ett postitivt tal");
        Main.sc.nextLine();
      }
    }
  }

  public static void minMaxAverage(Price[] prices) {
    int min = prices[0].price;
    int max = prices[0].price;
    String maxTime = prices[0].time;
    String minTime = prices[0].time;
    int average = 0;
    for (Price price : prices) {
      if (price.price > max) {
        maxTime = price.time;
        max = price.price;
      }

      if (price.price < min) {
        minTime = price.time;
        min = price.price;
      }
      average += price.price;
    }
    average /= prices.length;
    System.out.println("Högsta pris: " + maxTime + " " + max + " öre");
    System.out.println("Lägsta pris: " + minTime + " " + min + " öre");
    System.out.println("Medelpris: " + average + " öre");

  }
}


package se.labOne.java;

import java.util.*;

import static se.labOne.java.Main.sc;

public class Price {
  String time;
  int price;

  Price(String time, int price) {
    this.time = time;
    this.price = price;
  }

  public static ArrayList<Price> generateTimes(ArrayList<Price> pricesList) {
    int startTime = 0;
    int endTime = 1;
    for (int i = 0; i < 24; i++) {
      if (startTime < 10 && endTime < 10) {
        pricesList.add(new Price("0" + startTime + "-" + "0" + endTime, 0));
      } else if (startTime < 10) {
        pricesList.add(new Price("0" + startTime + "-" + endTime, 0));
      } else {
        pricesList.add(new Price(startTime + "-" + endTime, 0));
      }
      startTime++;
      endTime++;
    }

    return pricesList;
  }

  public static ArrayList<Price> setPrices(ArrayList<Price> prices) {
    for (Price price : prices) {
      System.out.println("Vänligen mata in pris (I hela ören) för den angivna tiden");
      System.out.println(price.time);
      price.price = readInt(sc);
    }

    return prices;
  }

  public static int readInt(Scanner scanner) {
    int userPrice = 0;
    while (true) {
      try {
        userPrice = sc.nextInt();
        if (userPrice < 0) {
          throw new InputMismatchException();
        } else {
          return userPrice;
        }
      } catch (InputMismatchException e) {
        System.out.println("Värdet måste vara ett postitivt tal");
        sc.nextLine();
      }
    }
  }

  public static void minMaxAverage(ArrayList<Price> prices) {
    if (prices.size() != 24) {
      System.out.println("Du måste ange priserna för dygnets timmar innan du kan använda denna funktion");
    } else {
      int min = prices.getFirst().price;
      int max = prices.getFirst().price;
      String maxTime = prices.getFirst().time;
      String minTime = prices.getFirst().time;
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
      average /= prices.size();
      System.out.println("Högsta pris: " + maxTime + " " + max + " öre");
      System.out.println("Lägsta pris: " + minTime + " " + min + " öre");
      System.out.println("Medelpris: " + average + " öre");
    }
  }

  public static void sortByPrice(ArrayList<Price> prices) {
    if (prices.size() != 24) {
      System.out.println("Du måste ange priserna för dygnets timmar innan du kan använda denna funktion");
    } else {
      Comparator<Price> comp = (i, j) -> Integer.compare(i.price, j.price);
      prices.sort(comp);
      for (Price price : prices) {
        System.out.println(price.time + " " + price.price);
      }
    }
  }

  public static void lowestWindow(ArrayList<Price> prices) {
    if (prices.size() != 24) {
      System.out.println("Du måste ange priserna för dygnets timmar innan du kan använda denna funktion");
    } else {
      int windowsSize = 4;
      int endIndex = 0;
      int minSum = 0;

      for (int i = 0; i < windowsSize; i++) {
        minSum += prices.get(i).price;
        endIndex = i;
      }

      int windowSum = minSum;
      for (int i = windowsSize; i < prices.size(); i++) {
        windowSum += prices.get(i).price - prices.get(i - windowsSize).price;
        if (windowSum < minSum) {
          endIndex = i;
        }
        minSum = Math.min(minSum, windowSum);
      }


      System.out.println("Pris under de billigaste 4 sammanhängade timmarna:");
      System.out.println("Tid att börja ladda: " + prices.get(endIndex - (windowsSize - 1)).time.substring(0, 2));
      System.out.println("Totalpris: " + minSum + " öre");
      System.out.println("Medelpris: " + minSum / windowsSize + " öre");
    }
  }
}


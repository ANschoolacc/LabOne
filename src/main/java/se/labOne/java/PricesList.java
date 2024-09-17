package se.labOne.java;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.*;

public class PricesList {

  private final List<Price> pricesList;

  PricesList(){
    pricesList = new ArrayList<>();
  }

  public void addPrice(Price p){
    pricesList.add(p);
  }

  public void clear(){
    pricesList.clear();
  }

  public void setPrices(Scanner sc) {
    pricesList.forEach((price -> {
      System.out.println("Vänligen mata in pris (I hela ören) för den angivna tiden");
      System.out.println(price.getTimeAsString());
      price.setPrice(readPositiveInt(sc));
    }));
  }

  public void generateTimes() {
    for (int i = 0; i < 24; i++) {
      if ((i + 1) > 23) {
        this.addPrice(new Price(LocalTime.of(23, 0, 0), LocalTime.of(0, 0, 0)));
      } else {
        this.addPrice(new Price(LocalTime.of(i, 0, 0), LocalTime.of(i + 1, 0, 0)));
      }
    }
  }

  public int readPositiveInt(Scanner scanner) {
    int userPrice;
    while (true) {
      try {
        userPrice = scanner.nextInt();
        if (userPrice < 0) {
          throw new InputMismatchException();
        } else {
          return userPrice;
        }
      } catch (InputMismatchException e) {
        System.out.println("Värdet måste vara ett postitivt tal");
        scanner.nextLine();
      }
    }
  }

  public void minMaxAverage() {
    if (pricesList.size() != 24) {
      System.out.println("Du måste ange priserna för dygnets timmar innan du kan använda denna funktion");
    } else {
      int min = pricesList.getFirst().getPrice();
      int max = pricesList.getFirst().getPrice();
      String maxTime = pricesList.getFirst().getTimeAsString();
      String minTime = pricesList.getFirst().getTimeAsString();
      double average = 0;
      for (Price price : pricesList) {
        if (price.getPrice() > max) {
          maxTime = price.getTimeAsString();
          max = price.getPrice();
        }

        if (price.getPrice() < min) {
          minTime = price.getTimeAsString();
          min = price.getPrice();
        }
        average += price.getPrice();
      }
      average /= pricesList.size();
      System.out.println("Högsta pris: " + maxTime + " " + max + " öre");
      System.out.println("Lägsta pris: " + minTime + " " + min + " öre");
      System.out.println("Medelpris: " + twoDecimalFormat(average) + " öre");
    }
  }

  public static String twoDecimalFormat(double price) {
    DecimalFormat df = new DecimalFormat("#.00");
    return df.format(price);
  }

  public void sortByPrice() {
    if (pricesList.size() != 24) {
      System.out.println("Du måste ange priserna för dygnets timmar innan du kan använda denna funktion");
    } else {
      pricesList.stream()
          .sorted(Comparator.comparingInt(Price::getPrice))
          .toList().forEach(Price::printPriceAndTime);
    }
  }

  public void lowestWindow() {
    if (pricesList.size() != 24) {
      System.out.println("Du måste ange priserna för dygnets timmar innan du kan använda denna funktion");
    } else {
      int windowsSize = 4;
      int endIndex = 0;
      int minSum = 0;

      for (int i = 0; i < windowsSize; i++) {
        minSum += pricesList.get(i).getPrice();
        endIndex = i;
      }

      int windowSum = minSum;
      for (int i = windowsSize; i < pricesList.size(); i++) {
        windowSum += pricesList.get(i).getPrice() - pricesList.get(i - windowsSize).getPrice();
        if (windowSum < minSum) {
          endIndex = i;
        }
        minSum = Math.min(minSum, windowSum);
      }
      System.out.println("Pris under de billigaste 4 sammanhängade timmarna:");
      System.out.println("Tid att börja ladda: " + pricesList.get(endIndex - (windowsSize - 1)).getTimeAsString().substring(0, 2));
      System.out.println("Totalpris: " + minSum + " öre");
      System.out.println("Medelpris: " + twoDecimalFormat((double) minSum / windowsSize) + " öre");
    }
  }

  public List<String[]> readCsv() {
    String file = "src\\elpriser.csv";
    BufferedReader reader = null;
    String line;
    List<String[]> csvData = new ArrayList<>();
    try {
      reader = new BufferedReader(new FileReader(file));
      while ((line = reader.readLine()) != null) {
        String[] row = line.split(",");
        csvData.add(row);
      }
    }catch (Exception e){
      System.out.println("No csv file found");
      return null;
    }
    finally {
      try{
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        System.out.println("Something went wrong with reading file");
      }
    }
    return  csvData;
  }

  public void importPricesFromCsv() {

    List<String[]> csvData;
    if (readCsv() != null) {
      csvData = readCsv();
    }else {
      return;
    }

    for (int i = 1; i < csvData.size(); i++) {
      String[] row = csvData.get(i);
      Price price = new Price(LocalTime.parse(row[0]), LocalTime.parse(row[0]).plusHours(1));
      double p = Double.parseDouble(row[1]);
      price.setPrice((int) Math.round(p));
      pricesList.add(price);
    }
  }
}

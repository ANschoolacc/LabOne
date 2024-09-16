package se.labOne.java;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Price {
  private final LocalTime startTime;
  private final LocalTime endTime;
  private int price;

  Price(LocalTime startTime, LocalTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  public LocalTime getEndTime() {
    return endTime;
  }
  public LocalTime getStartTime() {
    return startTime;
  }
   public String getTimeAsString(){
     return isoTimeFormat(getStartTime()) + "-" + isoTimeFormat(getEndTime());
   }

  public void printPriceAndTime() {
      System.out.println(getTimeAsString() + " " + getPrice() + " öre");
  }

  public String isoTimeFormat(LocalTime time) {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;

    return time.format(formatter).substring(0, 2);
  }
}


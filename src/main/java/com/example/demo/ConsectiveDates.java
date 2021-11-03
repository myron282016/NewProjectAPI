package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsectiveDates {



    boolean isConsectiveDates(List<String> availableDates)
    {
               // String[] dates = {"5/12/2017", "6/12/2017", "7/12/2017"};
             String[] dates = availableDates.toArray(new String[0]);
                List<LocalDate> localDateList = new ArrayList<>();
                for (int i = 0; i<dates.length; i++) {
                    String[] data = dates[i].split("/");
                    Month m = Month.of(Integer.parseInt(data[1]));
                    LocalDate localDate =
                            LocalDate.of(Integer.parseInt(data[2]),m,Integer.parseInt(data[0]));
                    localDateList.add(localDate);
                    Date date = java.sql.Date.valueOf(localDate);
                }
                System.out.println("Contents of the list are ::"+localDateList);
                Collections.sort(localDateList);
                for (int i = 0; i < localDateList.size() - 1; i++) {
                    LocalDate date1 = localDateList.get(i);
                    LocalDate date2 = localDateList.get(i + 1);
                    if (date1.plusDays(1).equals(date2)) {
                        System.out.println("Consecutive Dates are: " + date1 + " and " + date2);
                        return true;
                    }
                }

       return false;
    }
}

package sample.calculating;

import sample.data.SaveData;
import sample.data.components.Engine;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class CalculatingDateResources {
    public static long calculateDateInMonth(Date date) {
        Date dateAfter = new Date(date.getTime());
        dateAfter.setMonth(dateAfter.getMonth() + 7);
        Date dateNow = new Date();
    return (dateAfter.getTime() + 86_400_000) - dateNow.getTime();
    }
    public static long calculateDateInDays(Date date, Date date2) {
        Date dateAfter1 = new Date(date.getTime());
        Date dateAfter2 = new Date(date2.getTime());
        Date dateNow = new Date();
       if (dateAfter1.getTime() > dateAfter2.getTime()) {
           Calendar c = Calendar.getInstance();
           c.setTime(dateAfter1);
           c.add(Calendar.DATE, 37);
           dateAfter1 = c.getTime();
           return dateAfter1.getTime() - dateNow.getTime();
       } else {
           Calendar c = Calendar.getInstance();
           c.setTime(dateAfter2);
           c.add(Calendar.DATE, 37);
           dateAfter2 = c.getTime();
           return dateAfter2.getTime() - dateNow.getTime();
       }
    }
}

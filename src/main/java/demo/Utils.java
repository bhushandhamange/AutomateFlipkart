package demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String getCurrentDateMinus7Days() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Subtract 7 days from the current date
        LocalDate dateMinus7Days = currentDate.minusDays(7);
        
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        // Format the date
        return dateMinus7Days.format(formatter);
    }

    public static String getCurrentTime() {
        // Get the current time
        LocalTime currentTime = LocalTime.now();
        
        // Define the time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
        
        // Format the time
        return currentTime.format(formatter);
    }
    
}

package saveup.dateconverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {

	public static void main(String[] args) {

        //Get current date time
        LocalDateTime now = LocalDateTime.now();

        System.out.println("Before : " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formatDateTime = now.format(formatter);

        System.out.println("After : " + formatDateTime);

    }
}

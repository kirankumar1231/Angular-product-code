package nl.retail.store.grocerystore.util;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateUtil {
    public static LocalDate toLocalDate(@NonNull final String date) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        String[] patterns = { "yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "yyyy/MM/dd" };
        for (String pattern : patterns) {
            builder.appendOptional(DateTimeFormatter.ofPattern(pattern));
        }
        DateTimeFormatter dtFormatter = builder.toFormatter();
        return LocalDate.parse(date, dtFormatter);
    }
}

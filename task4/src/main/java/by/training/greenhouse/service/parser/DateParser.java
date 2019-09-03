package by.training.greenhouse.service.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for parsing data.
 */
public final class DateParser {
    /**
     * Default constructor.
     */
    private DateParser() {
    }

    /**
     * Parsing data.
     *
     * @param date date.
     * @return LocalDate object.
     */
    public static LocalDate parseDate(final String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}

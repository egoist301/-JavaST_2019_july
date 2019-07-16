package by.training.transport.dao.parser;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    /**
     * Separator.
     */
    private static final String REGEX_SEPARATOR = "";

    /**
     * @param stringListNew list of strings.
     * @return list of separated lines.
     */
    public List<String[]> getLines(final List<String> stringListNew) {
        List<String[]> result = new ArrayList<>();
        for (String temp : stringListNew) {
            result.add(temp.split(REGEX_SEPARATOR));
        }
        return result;
    }
}

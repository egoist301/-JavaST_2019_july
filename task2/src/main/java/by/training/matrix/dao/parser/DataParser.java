package by.training.matrix.dao.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Data parser.
 */
public class DataParser {
    /**
     * Splits a list of strings into a list of string arrays.
     *
     * @param stringListNew list of strings.
     * @return list of separated lines.
     */
    public List<String[]> getLines(final List<String> stringListNew) {
        final String regexSeparator = "\\s+";
        List<String[]> result = new ArrayList<>();
        for (String temp : stringListNew) {
            result.add(temp.split(regexSeparator));
        }
        return result;
    }
}

package by.training.catalog.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class RubikValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double MAX_WEIGHT = 3000;
    private static final double MIN_WEIGHT = 1;
    private static final double MAX_PRICE = 2000;
    private static final double MIN_PRICE = 1;
    private static final int MIN_MODEL_LENGTH = 4;
    private static final int MAX_MODEL_LENGTH = 30;
    private static final int MAX_INFO_LENGTH = 2000;
    private static final String REGEX_SIZE = "([0-9]{1,2}x[0-9]{1,2})|"
            + "([0-9]{1,2}x[0-9]{1,2}x[0-9]{1,2})";

    private RubikValidator() {
    }

    public static boolean isValid(final List<String> parameters) {
        boolean valid = true;
        int i = 0;
        int modelLength = parameters.get(i).length();
        if (modelLength > MAX_MODEL_LENGTH || modelLength < MIN_MODEL_LENGTH) {
            LOGGER.warn("Incorrect model: {}", parameters.get(i));
            valid = false;
        }
        double price = Double.parseDouble(parameters.get(++i));
        if (price > MAX_PRICE || price < MIN_PRICE) {
            LOGGER.warn("Incorrect price: {}", price);
            valid = false;
        }
        double weight = Double.parseDouble(parameters.get(++i));
        if (weight > MAX_WEIGHT || weight < MIN_WEIGHT) {
            LOGGER.warn("Incorrect weight: {}", weight);
            valid = false;
        }
        int infoLength = parameters.get(++i).length();
        if (infoLength > MAX_INFO_LENGTH || infoLength < 1) {
            LOGGER.warn("Incorrect info: {}", parameters.get(i));
            valid = false;
        }
        if (!parameters.get(++i).matches(REGEX_SIZE)) {
            LOGGER.warn("Incorrect size: {}", parameters.get(i));
            valid = false;
        }
        return valid;
    }
}

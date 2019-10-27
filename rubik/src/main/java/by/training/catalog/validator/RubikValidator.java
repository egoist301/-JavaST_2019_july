package by.training.catalog.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Rubik validator class.
 */
public final class RubikValidator {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Max weight.
     */
    private static final double MAX_WEIGHT = 3000;
    /**
     * Min weight
     */
    private static final double MIN_WEIGHT = 1;
    /**
     * Max price.
     */
    private static final double MAX_PRICE = 2000;
    /**
     * Min price.
     */
    private static final double MIN_PRICE = 1;
    /**
     * Minimal model length.
     */
    private static final int MIN_MODEL_LENGTH = 4;
    /**
     * Maximum model length.
     */
    private static final int MAX_MODEL_LENGTH = 30;
    /**
     * Maximum info length.
     */
    private static final int MAX_INFO_LENGTH = 2000;
    /**
     * Minimal info length.
     */
    private static final int MIN_INFO_LENGTH = 1;
    /**
     * Regex for size
     */
    private static final String REGEX_SIZE = "([0-9]{1,2}x[0-9]{1,2})|"
            + "([0-9]{1,2}x[0-9]{1,2}x[0-9]{1,2})";

    /**
     * Default constructor.
     */
    private RubikValidator() {
    }

    /**
     * Check invalidate price.
     *
     * @param price price.
     * @return invalid or valid.
     */
    public static boolean invalidatePrice(final String price) {
        return price == null
                || Double.parseDouble(price) > MAX_PRICE
                || Double.parseDouble(price) < MIN_PRICE;
    }

    /**
     * Check invalidate weight.
     *
     * @param weight weight.
     * @return invalid or valid.
     */
    public static boolean invalidateWeight(final String weight) {
        return weight == null
                || Double.parseDouble(weight) > MAX_WEIGHT
                || Double.parseDouble(weight) < MIN_WEIGHT;
    }

    /**
     * Check invalidate info.
     *
     * @param info info.
     * @return invalid or valid.
     */
    public static boolean invalidateInfo(final String info) {
        return info == null
                || info.length() > MAX_INFO_LENGTH
                || info.length() < MIN_INFO_LENGTH;
    }

    /**
     * Check invalidate size.
     *
     * @param size size.
     * @return invalid or valid.
     */
    public static boolean invalidateSize(final String size) {
        return size == null || !size.matches(REGEX_SIZE);
    }

    /**
     * Check invalidate model.
     *
     * @param model model.
     * @return invalid or valid.
     */
    public static boolean invalidateModel(final String model) {
        return model == null
                || model.length() > MAX_MODEL_LENGTH
                || model.length() < MIN_MODEL_LENGTH;
    }

    /**
     * Check validate cube parameters.
     *
     * @param parameters parameters for cube.
     * @return invalid or valid.
     */
    public static boolean isValid(final List<String> parameters) {
        boolean valid = true;
        int i = 0;
        String model = parameters.get(i);
        if (invalidateModel(model)) {
            LOGGER.warn("Incorrect model: {}", parameters.get(i));
            valid = false;
        }
        String price = parameters.get(++i);
        if (invalidatePrice(price)) {
            LOGGER.warn("Incorrect price: {}", price);
            valid = false;
        }
        String weight = parameters.get(++i);
        if (invalidateWeight(weight)) {
            LOGGER.warn("Incorrect weight: {}", weight);
            valid = false;
        }
        String info = parameters.get(++i);
        if (invalidateInfo(info)) {
            LOGGER.warn("Incorrect info: {}", parameters.get(i));
            valid = false;
        }
        String size = parameters.get(++i);
        if (invalidateSize(size)) {
            LOGGER.warn("Incorrect size: {}", parameters.get(i));
            valid = false;
        }
        return valid;
    }
}

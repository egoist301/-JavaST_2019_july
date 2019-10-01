package by.training.catalog.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {
    private UserValidator() {
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static Map<String, Boolean> validationMap = new HashMap<>();

    private static final int USER_LOGIN = 0;
    private static final int USER_PASSWORD = 1;
    private static final int USER_EMAIL = 2;
    private static final int USER_MOBILE_PHONE = 3;
    private static final String USER_LOGIN_REGEX = "^[a-zA-Z0-9]{4,16}$";
    private static final String USER_PASSWORD_REGEX = "^[a-zA-Z0-9]{8,16}$";
    private static final String USER_EMAIL_REGEX =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String USER_MOBILE_REGEX =
            "^((25)|(29)|(33)|(44))([0-9]{7}$)";

    private static void initMap() {
        validationMap.put("incorrectLogin", true);
        validationMap.put("incorrectPassword", true);
        validationMap.put("incorrectEmail", true);
        validationMap.put("incorrectMobilePhone", true);
    }

    public static Map<String, Boolean> validateUserParameters(
            final List<String> newParams) {
        for (String temp : newParams) {
            if (temp.contains("'")) {
                LOGGER.warn("Potential SQL injection attack: " + temp);
            }
        }

        initMap();

        if (newParams.get(USER_LOGIN).matches(USER_LOGIN_REGEX)) {
            validationMap.put("incorrectLogin", false);
        }
        if (newParams.get(USER_PASSWORD).matches(USER_PASSWORD_REGEX)) {
            validationMap.put("incorrectPassword", false);
        }
        if (newParams.get(USER_EMAIL).matches(USER_EMAIL_REGEX)) {
            validationMap.put("incorrectEmail", false);
        }
        if (newParams.get(USER_MOBILE_PHONE).matches(USER_MOBILE_REGEX)) {
            validationMap.put("incorrectMobilePhone", false);
        }
        return validationMap;
    }
}

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

    private static final String USER_LOGIN_REGEX = "^[a-zA-Z0-9]{4,16}$";
    private static final String USER_PASSWORD_REGEX = "^[a-zA-Z0-9]{4,16}$";
    private static final String USER_EMAIL_REGEX =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String USER_MOBILE_REGEX =
            "([0-9]{3}-[0-9]{2}-[0-9]{2})";

    /*public static Map<String, Boolean> validateUserParameters(
            final List<String> newParams) {
        for (String temp : newParams) {
            if (temp.contains("'")) {
                LOGGER.warn("Potential SQL injection attack: " + temp);
            }
        }
    }*/

    public static boolean isValid(final String login, final String password,
                                  final String email, final String phone) {
        boolean valid = true;
        if (!login.matches(USER_LOGIN_REGEX)) {
            LOGGER.warn("incorrect login: {}", login);
            valid = false;
        }
        if (!password.matches(USER_PASSWORD_REGEX)) {
            LOGGER.warn("incorrect password: {}", password);
            valid = false;
        }
        if (!email.matches(USER_EMAIL_REGEX)) {
            LOGGER.warn("incorrect email: {}", email);
            valid = false;
        }
        if (!phone.matches(USER_MOBILE_REGEX)) {
            LOGGER.warn("incorrect phone: {}", phone);
            valid = false;
        }
        return valid;
    }
}

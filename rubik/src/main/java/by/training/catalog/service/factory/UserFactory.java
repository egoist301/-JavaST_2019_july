package by.training.catalog.service.factory;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.User;
import by.training.catalog.validator.UserValidator;

/**
 * User factory.
 */
public class UserFactory {

    /**
     * Get valid user.
     *
     * @param usernameNew username.
     * @param emailNew    email.
     * @param phoneNew    phone.
     * @param passwordNew password.
     * @return user.
     */
    public User createUser(final String usernameNew, final String emailNew,
                           final String phoneNew,
                           final String passwordNew) {

        User user;
        if (UserValidator
                .isValid(usernameNew, passwordNew, emailNew, phoneNew)) {
            user = new User(1);
            user.setUsername(usernameNew);
            user.setPassword(passwordNew);
            user.setEmail(emailNew);
            String[] numbers = phoneNew.split("-");
            StringBuilder phone = new StringBuilder();
            for (String elem : numbers) {
                phone.append(elem);
            }
            user.setPhone(Integer.parseInt(phone.toString()));
            user.setRole(Role.USER);
        } else {
            user = null;
        }
        return user;
    }
}

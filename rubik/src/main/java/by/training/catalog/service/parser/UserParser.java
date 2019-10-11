package by.training.catalog.service.parser;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.User;

public class UserParser {

    public User getUser(final String usernameNew, final String emailNew,
                        final String phoneNew,
                        final String passwordNew) {
        User user = new User(1);
        user.setUsername(usernameNew);
        user.setPassword(passwordNew);
        user.setEmail(emailNew);
        String[] numbers = phoneNew.split("-");
        String phone = "";
        for (String elem : numbers) {
            phone += elem;
        }
        user.setPhone(Integer.parseInt(phone));
        user.setRole(Role.USER);
        return user;
    }
}

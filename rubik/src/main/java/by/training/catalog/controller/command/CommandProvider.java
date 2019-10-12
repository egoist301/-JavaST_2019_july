package by.training.catalog.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commandMap;

    public CommandProvider() {
        commandMap = new HashMap<>();
        commandMap.put("/login", new LoginPageCommand());
        commandMap.put("/signin", new SignInCommand());
        commandMap.put("/index", new MainCommand());
        commandMap.put("/signout", new SignOutCommand());
        commandMap.put("/lang", new ChangeLocaleCommand());
        commandMap.put("/contact", new ContactPageCommand());
        commandMap.put("/profile", new ProfilePageCommand());
        commandMap.put("/registration", new RegistrationPageCommand());
        commandMap.put("/registr", new RegistrationCommand());
        commandMap.put("/edit", new ProfileCommand());
        commandMap.put("/users", new UsersPageCommand());
    }

    public Command getCommand(final String actionNew) {
        return commandMap.get(actionNew);
    }
}

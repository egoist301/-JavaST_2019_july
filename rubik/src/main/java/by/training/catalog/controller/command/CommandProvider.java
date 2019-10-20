package by.training.catalog.controller.command;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static Map<String, Command> commandMap;

    private CommandProvider() {
    }

    static {
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
        commandMap.put("/addcube", new CreateCubePageCommand());
        commandMap.put("/catalog", new RubiksCommand());
        commandMap.put("/blocked", new BlockedUserCommand());
        commandMap.put("/rubik", new RubikCommand());
        commandMap.put("/likecube", new LikeCubeCommand());
        commandMap.put("/findform", new FindCubeByFormCommand());
        commandMap.put("/blockedcube", new BlockedCubeCommand());
        commandMap.put("/bookmarks", new BookmarksPageCommand());
        commandMap.put("/editcube", new EditCubePageCommand());
        commandMap.put("/findsize", new FindCubeBySizeCommand());
        commandMap.put("/findmodel", new FindCubeByModelCommand());
        commandMap.put("/removecube", new RemoveCubeFromBookmarksCommand());
        commandMap.put("/findusername", new FindUserByUsernameCommand());
        commandMap.put("/createcube", new AddRubikCommand());
    }

    public static Command getCommand(final String actionNew) {
        return commandMap.get(actionNew);
    }
}

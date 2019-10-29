package by.training.catalog.controller.command;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static Map<String, Command> commandMap;

    private CommandProvider() {
    }

    static {
        commandMap = new HashMap<>();
        commandMap.put("/", new MainCommand());
        commandMap.put("/login", new LoginPageCommand());
        commandMap.put("/signIn", new SignInCommand());
        commandMap.put("/index", new MainCommand());
        commandMap.put("/signOut", new SignOutCommand());
        commandMap.put("/lang", new ChangeLocaleCommand());
        commandMap.put("/contact", new ContactPageCommand());
        commandMap.put("/profile", new ProfilePageCommand());
        commandMap.put("/registration", new RegistrationPageCommand());
        commandMap.put("/register", new RegistrationCommand());
        commandMap.put("/edit", new ProfileCommand());
        commandMap.put("/users", new UsersPageCommand());
        commandMap.put("/addCube", new CreateCubePageCommand());
        commandMap.put("/catalog", new RubiksCommand());
        commandMap.put("/blocked", new BlockedUserCommand());
        commandMap.put("/rubik", new RubikCommand());
        commandMap.put("/likeCube", new LikeCubeCommand());
        commandMap.put("/findForm", new FindCubeByFormCommand());
        commandMap.put("/blockedCube", new BlockedCubeCommand());
        commandMap.put("/bookmarks", new BookmarksPageCommand());
        commandMap.put("/editCube", new EditCubePageCommand());
        commandMap.put("/findSize", new FindCubeBySizeCommand());
        commandMap.put("/findModel", new FindCubeByModelCommand());
        commandMap.put("/removeCube", new RemoveCubeFromBookmarksCommand());
        commandMap.put("/findUsername", new FindUserByUsernameCommand());
        commandMap.put("/createCube", new AddRubikCommand());
        commandMap
                .put("/findManufacturer", new FindCubeByManufacturerCommand());
        commandMap.put("/findPrice", new FindCubeByPriceCommand());
        commandMap.put("/updateInfo", new EditCubeCommand());
    }

    public static Command getCommand(final String actionNew) {
        return commandMap.get(actionNew);
    }
}

package by.training.catalog.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.training.catalog.constant.ApplicationConstants.REGISTRATION_JSP;

public class RegistrationPageCommand extends Command {
    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        return new CommandResult(REGISTRATION_JSP);
    }
}

package by.training.catalog.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.catalog.constant.ApplicationConstants.REGISTRATION_JSP;

public class RegistrationPageCommand extends Command {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        return new Forward(REGISTRATION_JSP);
    }
}

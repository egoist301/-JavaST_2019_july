package by.training.catalog.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.training.catalog.constant.ApplicationConstants.PROFILE_JSP;

public class ProfilePageCommand extends UserCommand {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        return new Forward(PROFILE_JSP);
    }
}

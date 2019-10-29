package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.training.catalog.constant.ApplicationConstants.*;

public class ProfileCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        UserService userService = getFactory().createUserService();
        String oldPassword = requestNew.getParameter(ATTRIBUTE_PASSWORD_OLD);
        String newPassword = requestNew.getParameter(ATTRIBUTE_PASSWORD);
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        LOGGER.debug(user);
        CommandResult commandResult;
        try {
            if (userService.authorize(user.getUsername(),
                    oldPassword) != null) {
                user.setPassword(newPassword);
                LOGGER.debug(user);
                userService.update(user);
                commandResult = new CommandResult(PROFILE, true);
                LOGGER.debug("password is change");
            } else {
                session.setAttribute(ERROR, PROFILE_MESSAGE);
                commandResult = new CommandResult(PROFILE);
                LOGGER.debug("incorrect old password");
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        return commandResult;
    }
}

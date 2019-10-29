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

public class SignInCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        String login = requestNew.getParameter(ATTRIBUTE_USERNAME);
        String password = requestNew.getParameter(ATTRIBUTE_PASSWORD);
        UserService userService = getFactory().createUserService();
        CommandResult commandResult;
        try {
            User user =
                    userService.authorize(login, password);
            HttpSession session = requestNew.getSession(true);
            if (user != null) {
                commandResult = new CommandResult(INDEX);
                session.setAttribute(ATTRIBUTE_USER, user);
                LOGGER.debug("sign in");
            } else {
                session.setAttribute(ERROR, LOGIN_MESSAGE);
                commandResult = new CommandResult(LOGIN);
                LOGGER.debug("don't sign in");
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        return commandResult;
    }
}

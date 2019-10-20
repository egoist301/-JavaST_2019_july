package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                    final HttpServletResponse responseNew) {
        String login = requestNew.getParameter("login");
        String password = requestNew.getParameter("password");
        UserService userService = getFactory().createUserService();
        Forward forward;
        try {
            User user =
                    userService.authorize(login, password);
            if (user != null) {
                forward = new Forward("index.html");
                HttpSession httpSession = requestNew.getSession(true);
                httpSession.setAttribute("user", user);
                LOGGER.debug("sign in");

            } else {
                forward = new Forward("login.html");
                LOGGER.debug("don't sign in");
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
        return forward;
    }
}

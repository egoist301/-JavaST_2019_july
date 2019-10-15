package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                    final HttpServletResponse responseNew)
            throws IOException {
        String login = requestNew.getParameter("login");
        String password = requestNew.getParameter("password");
        UserService userService = getFactory().createUserService();
        Forward forward = null;
        try {
            User user =
                    userService.findAccountByLoginAndPassword(login, password);
            if (user != null) {
                forward = new Forward("index.html");
                HttpSession httpSession = requestNew.getSession(true);
                httpSession.setAttribute("user", user);
                LOGGER.debug("авторизован");

            } else {
                forward = new Forward("login.html");
                LOGGER.debug("не авторизован");
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
        return forward;
    }
}

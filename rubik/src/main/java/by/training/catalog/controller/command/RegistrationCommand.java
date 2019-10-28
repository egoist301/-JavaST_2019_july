package by.training.catalog.controller.command;

import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        String username = requestNew.getParameter("username");
        String email = requestNew.getParameter("email");
        String phone = requestNew.getParameter("phone");
        String password = requestNew.getParameter("password");
        UserService service = getFactory().createUserService();
        HttpSession session = requestNew.getSession(false);
        Forward forward;
        try {
            if (service.create(username, email, phone, password)) {
                LOGGER.debug("account is create");
                forward = new Forward("index.html");
            } else {
                LOGGER.debug("account don't create");
                requestNew.setAttribute("error", "registration.error");
                forward = new Forward("registration.html");
            }
        } catch (ServiceException eNew) {
            LOGGER.warn(eNew);
            session.setAttribute("error", "registration.incorrect");
            forward = new Forward("registration.html");
        }
        return forward;
    }
}

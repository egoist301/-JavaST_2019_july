package by.training.catalog.controller.command;

import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.training.catalog.constant.ApplicationConstants.*;

public class RegistrationCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        String username = requestNew.getParameter(ATTRIBUTE_USERNAME);
        String email = requestNew.getParameter(ATTRIBUTE_EMAIL);
        String phone = requestNew.getParameter(ATTRIBUTE_PHONE);
        String password = requestNew.getParameter(ATTRIBUTE_PASSWORD);
        UserService service = getFactory().createUserService();
        HttpSession session = requestNew.getSession(false);
        Forward forward;
        try {
            if (service.create(username, email, phone, password)) {
                LOGGER.debug("account is create");
                forward = new Forward(INDEX);
            } else {
                LOGGER.debug("account don't create");
                requestNew.setAttribute(ERROR, REGISTRATION_MESSAGE);
                forward = new Forward(REGISTRATION);
            }
        } catch (ServiceException eNew) {
            LOGGER.warn(eNew);
            session.setAttribute(ERROR, REGISTRATION_MESSAGE);
            forward = new Forward(REGISTRATION);
        }
        return forward;
    }
}

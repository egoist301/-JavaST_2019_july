package by.training.catalog.controller.command;

import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        String username = requestNew.getParameter("username");
        String email = requestNew.getParameter("email");
        String phone = requestNew.getParameter("phone");
        String password = requestNew.getParameter("password");
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService service = serviceFactory.createUserService();
        Forward forward;
        try {
            if (service.findAccountByLogin(username) != null) {
                service.create(username, email, phone, password);
                LOGGER.debug("account is create");
                forward = new Forward("index.html");
            } else {
                LOGGER.debug("account don't create");
                forward = new Forward("registration.html");
            }
        } catch (ServiceException eNew) {

            forward = new Forward("registration.html");
        }
        return forward;
    }
}

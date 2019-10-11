package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends Command {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        String username = requestNew.getParameter("username");
        String email = requestNew.getParameter("email");
        String phone = requestNew.getParameter("phone");
        String password = requestNew.getParameter("password");
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService service = serviceFactory.createUserService();
        try {
            service.create(username, email, phone, password);
        } catch (ServiceException eNew) {
            return new Forward("registration.html");
        }
        return new Forward("index.html");
    }
}

package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockedUserCommand extends AdminCommand {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        int id = Integer.parseInt(requestNew.getParameter("id"));
        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.createUserService();
        try {
            User user = service.findById(id);
            if (user.isBlocked()){
                user.setBlocked(false);
            } else {
                user.setBlocked(true);
            }
            service.update(user);
        } catch (ServiceException eNew) {
            eNew.printStackTrace();
        }
        return new Forward("users.html", true);
    }
}

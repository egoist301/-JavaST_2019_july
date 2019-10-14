package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UsersPageCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LIMIT = 10;
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        ServiceFactory factory = new ServiceFactory();
        int page = 1;
        if (requestNew.getParameter("page") != null) {
            try {
                page = Integer.parseInt(requestNew.getParameter("page"));
            } catch (NumberFormatException e) {
                LOGGER.warn(e);
            }
        }
        UserService teamService = factory.createUserService();
        int records = 0;
        List<User> users = new ArrayList<>();
        try {
            int offset;
            if (page == 1) {
                offset = 0;
            } else {
                offset = (page - 1) * LIMIT;
            }
            records = teamService.findElementCount();
            users = teamService.findAll(offset, LIMIT);
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
        }
        requestNew.setAttribute("users", users);
        requestNew.setAttribute("page", page);
        requestNew.setAttribute("lastPage",
                records % LIMIT == 0 ? records / LIMIT : records / LIMIT + 1);
        return new Forward("WEB-INF/jsp/users.jsp");
    }
}
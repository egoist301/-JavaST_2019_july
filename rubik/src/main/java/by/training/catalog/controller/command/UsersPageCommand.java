package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.training.catalog.constant.ApplicationConstants.*;

public class UsersPageCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LIMIT = 10;

    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        int page = Pagination.calcPage(requestNew);
        UserService userService = getFactory().createUserService();
        int records;
        List<User> users;
        try {
            int offset;
            if (page == 1) {
                offset = 0;
            } else {
                offset = (page - 1) * LIMIT;
            }
            records = userService.findElementCount();
            users = userService.findAll(offset, LIMIT);
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        requestNew.setAttribute(USERS, users);
        requestNew.setAttribute(PAGE, page);
        requestNew.setAttribute(LAST_PAGE,
                records % LIMIT == 0 ? records / LIMIT : records / LIMIT + 1);
        return new CommandResult(USERS_JSP);
    }
}

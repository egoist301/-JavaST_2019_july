package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.service.Pagination;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.training.catalog.constant.ApplicationConstants.*;

public class BookmarksPageCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LIMIT = 10;

    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        int page = Pagination.calcPage(requestNew);
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        UserService service = getFactory().createUserService();
        StoreImageService imageService = getFactory().createStoreImageService();
        int records;
        try {
            int offset = Pagination.calcOffset(page, LIMIT);
            service.findLikedCubes(user, LIMIT, offset);
            records = service.findCountRubiks(user.getId());
            LOGGER.debug("Cube's on page {}", user.getCubes());
            for (RubiksCube cube : user.getCubes()) {
                imageService.assignRubikImagesPaths(cube);
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        LOGGER.debug("Paths {}", user.getCubes());
        requestNew.setAttribute(PAGE, page);
        requestNew.setAttribute(LAST_PAGE,
                records % LIMIT == 0 ? records / LIMIT : records / LIMIT + 1);
        return new CommandResult(BOOKMARKS_JSP);
    }
}

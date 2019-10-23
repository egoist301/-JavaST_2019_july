package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookmarksPageCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LIMIT = 10;

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        int page = Pagination.calcPage(requestNew);
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute("user");
        UserService service = getFactory().createUserService();
        StoreImageService imageService = getFactory().createStoreImageService();
        int records;
        try {
            int offset = Pagination.calcOffset(page, LIMIT);
            service.findLikedCubes(user, LIMIT, offset);
            records = service.findCountRubiks(user.getId());
            LOGGER.debug("Cube's on page {}", user.getCubes());
            for (RubiksCube cube : user.getCubes()) {
                imageService.findImagesByRubik(cube);
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        LOGGER.debug("Paths {}", user.getCubes());
        requestNew.setAttribute("page", page);
        requestNew.setAttribute("lastPage",
                records % LIMIT == 0 ? records / LIMIT : records / LIMIT + 1);
        return new Forward("WEB-INF/jsp/bookmarks.jsp");
    }
}

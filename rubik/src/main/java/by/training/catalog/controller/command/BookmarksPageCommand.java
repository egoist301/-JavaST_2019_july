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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookmarksPageCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LIMIT = 10;

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        int page = Pagination.calcPage(requestNew);
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute("user");
        UserService service = getFactory().createUserService();
        StoreImageService imageService = getFactory().createStoreImageService();
        int records;
        Map<RubiksCube, List<String>> map = new HashMap<>();
        try {
            int offset = Pagination.calcOffset(page, LIMIT);
            service.findLikedCubes(user, LIMIT, offset);
            records = user.getCubes().size();
            LOGGER.debug("Cube's on page {}", user.getCubes());
            for (RubiksCube cube : user.getCubes()) {
                map.put(cube, imageService.findImagesByRubik(cube));
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
        requestNew.setAttribute("paths", map);
        requestNew.setAttribute("page", page);
        requestNew.setAttribute("lastPage",
                records % LIMIT == 0 ? records / LIMIT : records / LIMIT + 1);
        return new Forward("WEB-INF/jsp/basket.jsp");
    }
}

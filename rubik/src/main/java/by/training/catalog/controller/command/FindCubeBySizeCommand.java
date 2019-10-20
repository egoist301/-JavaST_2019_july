package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindCubeBySizeCommand extends Command {
    private static final int LIMIT = 10;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        int page = Pagination.calcPage(requestNew);
        RubikService rubikService = getFactory().createRubikService();
        StoreImageService imageService = getFactory().createStoreImageService();
        int records;
        List<RubiksCube> rubiksCubes;
        try {
            int offset = Pagination.calcOffset(page, LIMIT);
            String size = requestNew.getParameter("size");
            rubiksCubes = rubikService.findRubiksBySize(size, offset, LIMIT);
            if (rubiksCubes.isEmpty()) {
                records = 1;
            } else {
                records = rubiksCubes.size();
            }
            for (RubiksCube cube : rubiksCubes) {
                imageService.findImagesByRubik(cube);
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
        return getForward(requestNew, page, records, rubiksCubes);
    }

    static Forward getForward(final HttpServletRequest requestNew,
                              final int pageNew, final int recordsNew,
                              final List<RubiksCube> rubiksCubesNew) {
        requestNew.setAttribute("rubiks", rubiksCubesNew);
        requestNew.setAttribute("page", pageNew);
        requestNew.setAttribute("lastPage",
                recordsNew % LIMIT == 0 ? recordsNew / LIMIT : recordsNew
                        / LIMIT + 1);
        return new Forward("WEB-INF/jsp/catalog.jsp");
    }
}

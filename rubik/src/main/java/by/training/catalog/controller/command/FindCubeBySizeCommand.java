package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.Pagination;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.training.catalog.constant.ApplicationConstants.CATALOG_JSP;
import static by.training.catalog.constant.ApplicationConstants.LAST_PAGE;
import static by.training.catalog.constant.ApplicationConstants.PAGE;
import static by.training.catalog.constant.ApplicationConstants.PARAMETER_SIZE;
import static by.training.catalog.constant.ApplicationConstants.RUBIKS;

public class FindCubeBySizeCommand extends FindCubeCommand {
    private static final int LIMIT = 10;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        int page = Pagination.calcPage(requestNew);
        RubikService rubikService = getFactory().createRubikService();
        StoreImageService imageService = getFactory().createStoreImageService();
        int records;
        List<RubiksCube> rubiksCubes;
        try {
            int offset = Pagination.calcOffset(page, LIMIT);
            String size = requestNew.getParameter(PARAMETER_SIZE);
            rubiksCubes = rubikService.findRubiksBySize(size, offset, LIMIT);
            records = rubikService.findCountBySize(size);
            for (RubiksCube cube : rubiksCubes) {
                imageService.assignRubikImagesPaths(cube);
            }
            specifyForms(requestNew);
            specifyManufacturers(requestNew);
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        return getForward(requestNew, page, records, rubiksCubes);
    }

    static CommandResult getForward(final HttpServletRequest requestNew,
                                    final int pageNew, final int recordsNew,
                                    final List<RubiksCube> rubiksCubesNew) {
        requestNew.setAttribute(RUBIKS, rubiksCubesNew);
        requestNew.setAttribute(PAGE, pageNew);
        requestNew.setAttribute(LAST_PAGE,
                recordsNew % LIMIT == 0 ? recordsNew / LIMIT : recordsNew
                        / LIMIT + 1);
        return new CommandResult(CATALOG_JSP);
    }
}

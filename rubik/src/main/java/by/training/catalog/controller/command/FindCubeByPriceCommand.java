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

import static by.training.catalog.constant.ApplicationConstants.ATTRIBUTE_MAX_PRICE;
import static by.training.catalog.constant.ApplicationConstants.ATTRIBUTE_MIN_PRICE;
import static by.training.catalog.controller.command.FindCubeBySizeCommand.getForward;

public class FindCubeByPriceCommand extends FindCubeCommand {
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
            String minPrice = requestNew.getParameter(ATTRIBUTE_MIN_PRICE);
            String maxPrice = requestNew.getParameter(ATTRIBUTE_MAX_PRICE);
            rubiksCubes = rubikService
                    .findRubiksByRangePrice(minPrice, maxPrice, offset, LIMIT);
            records = rubikService.findCountByPrice(minPrice, maxPrice);
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
}

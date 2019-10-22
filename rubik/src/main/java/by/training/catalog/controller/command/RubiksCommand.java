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

import static by.training.catalog.controller.command.FindCubeBySizeCommand.getForward;

public class RubiksCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LIMIT = 10;

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        int page = Pagination.calcPage(requestNew);
        RubikService rubikService = getFactory().createRubikService();
        StoreImageService imageService = getFactory().createStoreImageService();
        int records;
        List<RubiksCube> rubiksCubes;
        try {
            List<String> forms = rubikService.readAllForm();
            List<String> manufacturers = rubikService.readAllManufacturer();
            int offset;
            if (page == 1) {
                offset = 0;
            } else {
                offset = (page - 1) * LIMIT;
            }
            records = rubikService.findElementCount();
            rubiksCubes = rubikService.findAll(offset, LIMIT);
            for (RubiksCube cube : rubiksCubes) {
                imageService.findImagesByRubik(cube);
            }
            requestNew.setAttribute("forms", forms);
            requestNew.setAttribute("manufacturer", manufacturers);
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        return getForward(requestNew, page, records, rubiksCubes);
    }
}

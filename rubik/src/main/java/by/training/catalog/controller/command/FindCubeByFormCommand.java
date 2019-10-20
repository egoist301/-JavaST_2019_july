package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.training.catalog.controller.command.FindCubeBySizeCommand.getForward;

public class FindCubeByFormCommand extends Command {
    private static final int LIMIT = 10;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        int page = Pagination.calcPage(requestNew);
        RubikService rubikService = getFactory().createRubikService();
        StoreImageService imageService = getFactory().createStoreImageService();
        int records;
        List<RubiksCube> rubiksCubes;
        Map<RubiksCube, List<String>> map = new HashMap<>();
        try {
            int offset = Pagination.calcOffset(page, LIMIT);
            String form = requestNew.getParameter("form");
            rubiksCubes = rubikService.findRubiksByForm(form, offset, LIMIT);
            records = rubiksCubes.size();
            for (RubiksCube cube : rubiksCubes) {
                map.put(cube, imageService.findImagesByRubik(cube));
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
        return getForward(requestNew, page, records, rubiksCubes, map);
    }
}

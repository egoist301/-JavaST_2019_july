package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import by.training.catalog.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindCubeByFormCommand extends Command {
    private static final int LIMIT = 10;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        ServiceFactory factory = new ServiceFactory();
        int page = 1;
        if (requestNew.getParameter("page") != null) {
            try {
                page = Integer.parseInt(requestNew.getParameter("page"));
            } catch (NumberFormatException e) {
                LOGGER.warn(e);
            }
        }
        RubikService rubikService = factory.createRubikService();
        StoreImageService imageService = factory.createStoreImageService();
        int records = 0;
        List<RubiksCube> rubiksCubes = new ArrayList<>();
        List<StoreImage> strings = new ArrayList<>();
        String form = requestNew.getParameter("form");
        LOGGER.debug(form);
        try {
            int offset;
            if (page == 1) {
                offset = 0;
            } else {
                offset = (page - 1) * LIMIT;
            }
            rubiksCubes = rubikService.findRubiksByForm(form, offset, LIMIT);
            LOGGER.debug(rubiksCubes);
            records = rubiksCubes.size();
            strings = imageService.findAll();//TODO write read img paths!!!
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
        }
        requestNew.setAttribute("paths", strings);
        requestNew.setAttribute("rubiks", rubiksCubes);
        requestNew.setAttribute("page", page);
        requestNew.setAttribute("lastPage",
                records % LIMIT == 0 ? records / LIMIT : records / LIMIT + 1);
        return new Forward("WEB-INF/jsp/catalog.jsp");
    }
}

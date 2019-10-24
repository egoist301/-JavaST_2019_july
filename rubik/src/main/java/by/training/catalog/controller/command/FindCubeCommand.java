package by.training.catalog.controller.command;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

abstract class FindCubeCommand extends Command {
    void getForms(final HttpServletRequest requestNew) throws ServiceException {
        RubikService rubikService = getFactory().createRubikService();
        List<String> forms = rubikService.readAllForm();
        requestNew.setAttribute("forms", forms);
    }

    void getManufacturers(final HttpServletRequest requestNew)
            throws ServiceException {
        RubikService rubikService = getFactory().createRubikService();
        List<String> manufacturers = rubikService.readAllManufacturer();
        requestNew.setAttribute("manufacturer", manufacturers);
    }
}

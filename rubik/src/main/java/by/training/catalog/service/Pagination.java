package by.training.catalog.service;

import by.training.catalog.validator.PaginationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.training.catalog.constant.ApplicationConstants.PAGE;

public final class Pagination {
    private static final Logger LOGGER = LogManager.getLogger();

    private Pagination() {
    }

    public static int calcPage(final HttpServletRequest requestNew) {
        int page = 1;
        if (requestNew.getParameter(PAGE) != null) {
            try {
                page = Integer.parseInt(requestNew.getParameter(PAGE));
            } catch (NumberFormatException e) {
                LOGGER.warn(e);
            }
        }
        return page;
    }

    public static int calcOffset(final int page, final int limit)
            throws ServiceException {
        if (PaginationValidator.isValidPage(page) && PaginationValidator
                .isValidLimit(limit)) {
            int offset;
            if (page == 1) {
                offset = 0;
            } else {
                offset = (page - 1) * limit;
            }
            return offset;
        } else {
            throw new ServiceException("Incorrect page or limit");
        }
    }
}

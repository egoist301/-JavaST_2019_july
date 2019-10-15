package by.training.catalog.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public final class Pagination {
    private static final Logger LOGGER = LogManager.getLogger();

    private Pagination() {
    }

    static int calcPage(final HttpServletRequest requestNew) {
        int page = 1;
        if (requestNew.getParameter("page") != null) {
            try {
                page = Integer.parseInt(requestNew.getParameter("page"));
            } catch (NumberFormatException e) {
                LOGGER.warn(e);
            }
        }
        return page;
    }
    static int calcOffset(int page, int limit) {
        int offset;
        if (page == 1) {
            offset = 0;
        } else {
            offset = (page - 1) * limit;
        }
        return offset;
    }
}

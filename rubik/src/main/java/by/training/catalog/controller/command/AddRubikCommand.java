package by.training.catalog.controller.command;

import by.training.catalog.bean.RawData;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.training.catalog.constant.ApplicationConstants.*;

public class AddRubikCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew)
            throws IOException {
        HttpSession session = requestNew.getSession(false);
        List<String> parameters = new ArrayList<>();
        fillWithParameters(requestNew, parameters);
        List<Part> parts;
        List<RawData> rawData = new ArrayList<>();
        try {
            parts = new ArrayList<>(requestNew.getParts());
            for (Part part : parts) {
                if (part.getContentType() != null) {
                    RawData rd = new RawData();
                    rd.setStream(part.getInputStream());
                    rd.setContentType(part.getContentType());
                    rawData.add(rd);
                }
            }
            RubikService rubikService = getFactory().createRubikService();
            if (rubikService.create(parameters, rawData)) {
                return new CommandResult(CATALOG);
            } else {
                session.setAttribute(ERROR, RUBIK_MESSAGE);
                return new CommandResult(ADD_CUBE);
            }

        } catch (ServletException | ServiceException eNew) {
            LOGGER.error(eNew.getMessage(), eNew);
            return sendError(SERVER_ERROR);
        }
    }

    static void fillWithParameters(final HttpServletRequest requestNew,
                                   final List<String> parametersNew) {
        parametersNew.add(requestNew.getParameter("model"));
        parametersNew.add(requestNew.getParameter("price"));
        parametersNew.add(requestNew.getParameter("weight"));
        parametersNew.add(requestNew.getParameter("info"));
        parametersNew.add(requestNew.getParameter("size"));
        parametersNew.add(requestNew.getParameter("manufacturer"));
        parametersNew.add(requestNew.getParameter("form"));
        parametersNew.add(requestNew.getParameter("plasticColor"));
        parametersNew.add(requestNew.getParameter("primaryPlastic"));
    }
}

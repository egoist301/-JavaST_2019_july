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

public class AddRubikCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        HttpSession session = requestNew.getSession(false);
        List<String> parameters = new ArrayList<>();
        parameters.add(requestNew.getParameter("model"));
        parameters.add(requestNew.getParameter("price"));
        parameters.add(requestNew.getParameter("weight"));
        parameters.add(requestNew.getParameter("info"));
        parameters.add(requestNew.getParameter("size"));
        parameters.add(requestNew.getParameter("manufacturer"));
        parameters.add(requestNew.getParameter("form"));
        parameters.add(requestNew.getParameter("plasticColor"));
        parameters.add(requestNew.getParameter("primaryPlastic"));
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
                return new Forward("catalog.html");
            } else {
                session.setAttribute("error", "rubik.incorrect");
                return new Forward("addcube.html");
            }

        } catch (ServletException | ServiceException eNew) {
            LOGGER.error(eNew.getMessage(), eNew);
            return sendError(SERVER_ERROR);
        }
    }
}

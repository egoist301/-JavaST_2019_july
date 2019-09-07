package by.training.greenhouse.controller;

import by.training.greenhouse.bean.ArtificialFlower;
import by.training.greenhouse.bean.Flower;
import by.training.greenhouse.bean.LivingFlower;
import by.training.greenhouse.service.factory.FlowerFactory;
import by.training.greenhouse.service.parser.AbstractBuilder;
import by.training.greenhouse.service.parser.ParserException;
import by.training.greenhouse.service.validator.ValidatorXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Runner.
 */
@WebServlet("/hello")
public class FlowerServlet extends HttpServlet {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * XSD schema.
     */
    private static final String INPUT_XSD = "data//greenhouse.xsd";

    /**
     * Do get.
     *
     * @param request  request.
     * @param response response.
     * @throws IOException      input\output exception.
     * @throws ServletException servlet exception.
     */
    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Do post.
     *
     * @param req  request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException      input\output exception.
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Request processing.
     *
     * @param request  -request.
     * @param response -response.
     * @throws ServletException -some servlet exception.
     * @throws IOException      -I/O exception.
     */
    private void processRequest(final HttpServletRequest request,
                                final HttpServletResponse response)
            throws ServletException, IOException {
        String parserType = request.getParameter("parserType");
        String xmlPath = request.getParameter("xmlText");
        Set<Flower> livingFlowers = new LinkedHashSet<>();
        Set<Flower> artificialFlower = new LinkedHashSet<>();

        AbstractBuilder parser =
                FlowerFactory.getINSTANCE().createFlowerBuilder(parserType);
        if (ValidatorXML.validate(xmlPath,
                INPUT_XSD)) {
            try {
                parser.buildSetFlowers(xmlPath);
            } catch (ParserException eNew) {
                LOGGER.error(eNew);
                throw new RuntimeException();
            }
        }
        Set<Flower> flowers = parser.getFlowers();
        for (Flower flower : flowers) {
            if (flower instanceof LivingFlower) {
                livingFlowers.add(flower);
            }
            if (flower instanceof ArtificialFlower) {
                artificialFlower.add(flower);
            }
        }
        request.setAttribute("living", livingFlowers);
        request.setAttribute("artificial", artificialFlower);
        request.getRequestDispatcher("/jsp/result.jsp")
                .forward(request, response);
    }

}

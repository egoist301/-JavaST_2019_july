package by.training.catalog.controller;

import by.training.catalog.controller.command.Command;
import by.training.catalog.dao.pool.ConnectionPool;
import by.training.catalog.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Application servlet. Runner.
 */
@WebServlet("*.html")
@MultipartConfig
public class ApplicationServlet extends HttpServlet {
    /**
     * Database.
     */
    private static final String DATABASE = "database";
    private static final String COMMAND = "command";
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Initialize project and database.
     */
    @Override
    public void init() {
        ResourceBundle bundle = ResourceBundle.getBundle(DATABASE);
        String path = getServletContext().getRealPath("/");
        ServiceInitializer.init(bundle, path);
    }

    /**
     * Get.
     *
     * @param req  request.
     * @param resp response.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) {
        Command command = (Command) req.getAttribute(COMMAND);
        try {
            Command.CommandResult commandResult = command.execute(req, resp);
            if (checkError(commandResult, resp)) {
                if (commandResult.isRedirect()) {
                    resp.sendRedirect(commandResult.getUrl());
                } else {
                    req.getRequestDispatcher(commandResult.getUrl())
                            .forward(req, resp);
                }
            }
        } catch (ServletException | IOException e) {
            LOGGER.error("Cannot forward user. {}", e.getMessage());
        }
    }

    /**
     * Post.
     *
     * @param req  request.
     * @param resp response.
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        Command command = (Command) req.getAttribute(COMMAND);
        try {
            Command.CommandResult commandResult = command.execute(req, resp);
            if (checkError(commandResult, resp)) {
                resp.sendRedirect(commandResult.getUrl());
            }
        } catch (IOException e) {
            LOGGER.error("Cannot redirect user. {}", e.getMessage());
        }
    }

    /**
     * Check forward. Contains error or not.
     *
     * @param commandResultNew  forward.
     * @param response response.
     * @return true or false.
     */
    private boolean checkError(final Command.CommandResult commandResultNew,
                               final HttpServletResponse response) {
        if (commandResultNew.isError()) {
            try {
                int error = (Integer) commandResultNew.getAttributes().get("error");
                response.sendError(error);
            } catch (IOException e) {
                LOGGER.error("Cannot redirect user.");
            }
        } else {
            return true;
        }
        return false;
    }

    /**
     * Close connection pool.
     */
    @Override
    public void destroy() {
        ConnectionPool.getInstance().close();
    }
}

package by.training.catalog.controller;

import by.training.catalog.controller.command.Command;
import by.training.catalog.controller.command.RubiksCommandImpl;
import by.training.catalog.dao.pool.ConnectionPool;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet("*.html")
public class ApplicationServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init() {
        try {
            ServiceInitializer.init();
        } catch (ServiceException eNew) {
            LOGGER.fatal("don't init connection pool");
            throw new RuntimeException();
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = (Command) req.getAttribute("command");
        Command.Forward forward = command.execute(req, resp);
        if (forward.isRedirect()) {
            resp.sendRedirect(forward.getUrl());
        } else {
            req.getRequestDispatcher(forward.getUrl()).forward(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = (Command) req.getAttribute("command");
        Command.Forward forward = command.execute(req, resp);
        resp.sendRedirect(forward.getUrl());
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().close();
    }
}

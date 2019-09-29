package by.training.catalog.controller;

import by.training.catalog.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rubiks")
public class ApplicationServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(final HttpServletRequest req,
                                final HttpServletResponse resp) {

    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}

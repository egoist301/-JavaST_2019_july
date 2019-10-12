package by.training.catalog.filter;

import by.training.catalog.controller.command.Command;
import by.training.catalog.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class URLActionFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CommandProvider PROVIDER = new CommandProvider();
    @Override
    public void doFilter(final ServletRequest servletRequestNew,
                         final ServletResponse servletResponseNew,
                         final FilterChain filterChainNew)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequestNew;
        HttpServletResponse response = (HttpServletResponse) servletResponseNew;
        LOGGER.debug("Request method: {}", request.getMethod());
        String context = request.getContextPath();
        String requestURI = request.getRequestURI();
        LOGGER.debug("Request URI: {}", requestURI);
        int contextLength = context.length();
        int endRequestIndex = requestURI.lastIndexOf(".html");
        String action = requestURI.substring(contextLength, endRequestIndex);
        LOGGER.debug("Requested action: {}", action);
        Command command = PROVIDER.getCommand(action);
        request.setAttribute("command", command);
        filterChainNew.doFilter(request, response);
    }
}

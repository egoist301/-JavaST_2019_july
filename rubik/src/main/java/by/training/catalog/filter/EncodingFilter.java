package by.training.catalog.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Character encoding to which request encoding will be set.
     */
    private static final String REQUEST_ENCODING = "UTF-8";

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequestNew,
                         final ServletResponse servletResponseNew,
                         final FilterChain filterChainNew)
            throws IOException, ServletException {
        LOGGER.trace("Entered EncodingFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequestNew;
        String uri = httpRequest.getRequestURI();
        LOGGER.info("EncodingFilter uri = " + uri);
        httpRequest.setCharacterEncoding(REQUEST_ENCODING);
        LOGGER.trace("Exiting EncodingFilter");
        filterChainNew.doFilter(servletRequestNew, servletResponseNew);
    }

    @Override
    public void destroy() {
    }
}

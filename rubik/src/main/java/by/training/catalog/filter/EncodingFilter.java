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

/**
 * Encoding filter. UTF-8.
 */
public class EncodingFilter implements Filter {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Character encoding to which request encoding will be set.
     */
    private static final String REQUEST_ENCODING = "UTF-8";

    /**
     * Initializer.
     *
     * @param filterConfig filter config.
     */
    @Override
    public void init(final FilterConfig filterConfig) {

    }

    /**
     * Do filter.
     *
     * @param servletRequestNew  request.
     * @param servletResponseNew response.
     * @param filterChainNew     chain.
     * @throws IOException      i/o exception.
     * @throws ServletException servlet exception.
     */
    @Override
    public void doFilter(final ServletRequest servletRequestNew,
                         final ServletResponse servletResponseNew,
                         final FilterChain filterChainNew)
            throws IOException, ServletException {
        LOGGER.trace("Entered EncodingFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequestNew;
        String uri = httpRequest.getRequestURI();
        LOGGER.debug("EncodingFilter uri = {}", uri);
        httpRequest.setCharacterEncoding(REQUEST_ENCODING);
        LOGGER.trace("Exiting EncodingFilter");
        filterChainNew.doFilter(servletRequestNew, servletResponseNew);
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {
    }
}

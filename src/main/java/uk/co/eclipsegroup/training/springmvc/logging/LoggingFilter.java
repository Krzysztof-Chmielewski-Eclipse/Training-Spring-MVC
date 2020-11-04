package uk.co.eclipsegroup.training.springmvc.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoggingFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        log.info("Request: `{}`", httpRequest.getRequestURI());

        chain.doFilter(request, response);

        log.info("Filter done for request: `{}`", httpRequest.getRequestURI());
    }
}

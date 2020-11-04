package uk.co.eclipsegroup.training.springmvc.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Pre handle `{}`", request.getRequestURI());
        log.info("Pre handle `{}`", handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("Post handle `{}`", request.getRequestURI());
        log.info("Post handle `{}`", handler);
        log.info("Post handle `{}`", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("After completion `{}`", request.getRequestURI());
        log.info("After completion `{}`", handler);
        if (ex != null) {
            log.info("After completion `{}`", ex.toString());
        }
    }
}

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
        log.info("PreHandle: `{}`", request.getRequestURI());
        log.info("Handler: `{}`", handler);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("PostHandle: `{}`", modelAndView);
        if (modelAndView != null) {
            modelAndView.getModel().put("test", "Some more message");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("AfterCompletion: `{}`", request.getRequestURI());
        if (ex != null) {
            log.info("Exception", ex);
        }
    }
}

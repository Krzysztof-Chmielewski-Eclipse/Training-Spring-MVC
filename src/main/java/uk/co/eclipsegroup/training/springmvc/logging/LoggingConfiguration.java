package uk.co.eclipsegroup.training.springmvc.logging;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoggingConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
    }

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        var registration = new FilterRegistrationBean<LoggingFilter>();

        registration.setFilter(new LoggingFilter());
        registration.addUrlPatterns("/bar/*");

        return registration;
    }
}

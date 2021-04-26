package app.controller.config;

import app.controller.filter.LoggingFilter;
import app.controller.filter.SecurityFilter;
import app.database.service.RefreshService;
import app.security.auth.Auth;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Used for redirecting to home page when gets errors
 */
@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter(){
        FilterRegistrationBean<LoggingFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new LoggingFilter());
        regBean.setOrder(1);
        return regBean;
    }

    @Bean
    public FilterRegistrationBean<SecurityFilter> securityFilter(Auth auth, RefreshService refreshService){
        FilterRegistrationBean<SecurityFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new SecurityFilter(auth, refreshService));
        regBean.addUrlPatterns("/", "/game/*", "/log/*");
        regBean.setOrder(2);
        return regBean;
    }
}

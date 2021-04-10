package app.controller.util;

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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Used for redirecting to home page when gets errors
 */
@Configuration
public class ErrorRedirectConfig implements WebMvcConfigurer {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/unauthorised"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/not_found"));
        };
    }
}

package app.text_quest.util;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class ConfigurationOAuth2 extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/build/**", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("index.min");
    }
}

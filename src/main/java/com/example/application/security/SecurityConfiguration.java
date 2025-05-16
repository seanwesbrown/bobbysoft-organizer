package com.example.application.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import com.vaadin.hilla.route.RouteUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    private final RouteUtil routeUtil;

    public SecurityConfiguration(RouteUtil routeUtil) {
        this.routeUtil = routeUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> registry.requestMatchers(
                routeUtil::isRouteAllowed).permitAll());
        http.authorizeHttpRequests(registry -> {
            registry.requestMatchers(new AntPathRequestMatcher("/public-view")).permitAll(); // custom matcher
        });

        super.configure(http);

        setLoginView(http, "/login", "/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().requestMatchers(new AntPathRequestMatcher("/images/**"));
    }

    @Bean
    public UserDetailsManager userDetailsService(DataSource datasource) {
        return new JdbcUserDetailsManager(datasource);
    }

}

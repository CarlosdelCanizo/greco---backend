package com.greco.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * enables a Spring Security filter that authenticates requests via an incoming OAuth2 token
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	 /**
     * configures the access rules and request matchers (path) for protected resources
     * @param http
     * @throws Exception
     */
     @Override
     public void configure(HttpSecurity http) throws Exception {
         // Configuracion de las URLS del OAuth2 (urls que queremos que sean disponibles o no a nivel app).
         //-- define URL patterns to enable OAuth2 security
             http.
         //      anonymous().disable()
                 requestMatchers().antMatchers("/**")
                 .and().authorizeRequests()
                 .antMatchers("/v2/api-docs",
                         "/configuration/ui",
                         "/swagger-resources",
                         "/configuration/security",
                         "/swagger-ui.html",
                         "/webjars/**").permitAll()
                 .antMatchers("/**").authenticated()//.access("hasRole('ADMIN') or hasRole('USER')")
                 .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
         }
}
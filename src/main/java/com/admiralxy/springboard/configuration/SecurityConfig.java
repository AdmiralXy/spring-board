package com.admiralxy.springboard.configuration;

import com.admiralxy.springboard.security.jwt.JwtConfigurer;
import com.admiralxy.springboard.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Security configuration class for JWT based Spring Security application.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String HOME_ENDPOINT = "/";
    private static final String CLIENT_ENDPOINT = "/p-**";
    private static final String FAVICON_ENDPOINT = "/favicon.ico";
    private static final String NUXT_FILES_ENDPOINT = "/_nuxt/**";
    private static final String LOGIN_REGISTER_ENDPOINT = "/api/auth/**";
    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String USER_ENDPOINT = "/api/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HOME_ENDPOINT).permitAll()
                .antMatchers(CLIENT_ENDPOINT).permitAll()
                .antMatchers(FAVICON_ENDPOINT).permitAll()
                .antMatchers(NUXT_FILES_ENDPOINT).permitAll()
                .antMatchers(LOGIN_REGISTER_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(USER_ENDPOINT).hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}

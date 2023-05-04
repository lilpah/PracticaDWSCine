package com.cinema.Cinema.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
@Order(1)
public class RESTSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService repositoryUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(repositoryUserDetailsService).passwordEncoder(new BCryptPasswordEncoder(10,new SecureRandom()));
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http.antMatcher("/api/**");

        http
                //users
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/user").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/user").hasAnyRole("USER", "ADMIN")
               // .antMatchers(HttpMethod.GET,"/api/register").hasAnyRole("USER", "ADMIN")
               // .antMatchers(HttpMethod.POST,"/api/register").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/updateUser").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/deleteMyUser").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/admin/showAllUsers").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/admin/showAllUsers").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/admin/deleteUser/{id}").hasAnyRole("ADMIN")


                //movies

                .antMatchers(HttpMethod.GET,"/api/admin/showMovies").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/admin/showMovies").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/admin/deleteMovie/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/admin/updateMovie/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/admin/addMovie").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/admin/addMovie").hasAnyRole("ADMIN")



                //tickets
                .antMatchers(HttpMethod.GET,"/api/user/buyTicket").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/user/buyTicket").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/api/user/showTickets").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/user/showTickets").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/user/deleteTicket/{idTicket}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/user/updateTicket/{idTicket}").hasAnyRole("USER", "ADMIN")











                .anyRequest()
                .permitAll();

        http.csrf().disable();
        http.httpBasic();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

}

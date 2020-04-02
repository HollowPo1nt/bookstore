package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder().encode("admin"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/orders/**").permitAll()
                .antMatchers("/", "/books", "/books/order", "/books/byGenre/", "/books/byAuthor/", "/genres", "/authors", "/orders").permitAll()
                .antMatchers("/books/add/**", "/books/edit/**", "/genres/add/**", "/authors/add/**", "/orders").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/books")
                .usernameParameter("username")
                .passwordParameter("password").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

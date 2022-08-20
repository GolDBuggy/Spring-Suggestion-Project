package com.ticket.spring.Config;

import com.ticket.spring.Service.GroupDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final GroupDetailService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.authorizeRequests().antMatchers("/admin/**").permitAll().and().httpBasic();
        http.authorizeRequests().antMatchers("/").permitAll().and().
                authorizeRequests().antMatchers("/save").permitAll().and().
                authorizeRequests().antMatchers("/api/saveId").permitAll().and().
                authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/loginpage").
                permitAll().and().logout().permitAll();

    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}

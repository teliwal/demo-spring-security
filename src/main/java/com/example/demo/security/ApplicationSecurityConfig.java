package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.ApplicationUserRole.EMPLOYEE;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/","index","/css/*","/js/*").permitAll()
            .antMatchers("/api/**").hasRole(EMPLOYEE.name())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        UserDetails telly = User.builder()
                .username("telly")
                .password(passwordEncoder.encode("kikala"))
                .roles(EMPLOYEE.name())
                .build();

        UserDetails responsable = User.builder()
                .username("resp")
                .password(passwordEncoder.encode("password"))
                .roles(EMPLOYEE.name())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password123"))
                .roles(EMPLOYEE.name())
                .build();

        return new InMemoryUserDetailsManager(telly,responsable,admin);
    }
}

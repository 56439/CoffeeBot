package ru.malyshev.coffeebot.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/cafe").authenticated()
                .antMatchers("/addCafe").authenticated()
                .antMatchers("/editCafe/*").authenticated()
                .antMatchers("/addDrink").authenticated()
                .antMatchers("/editDrink/*").authenticated()
                .antMatchers("/api/drink/delete/*").authenticated()
                .antMatchers("/api/drink/save").authenticated()
                .antMatchers("/api/cafe/save").authenticated()
                .antMatchers("/api/cafe/delete/*").authenticated()
                .antMatchers("/actuator/**").authenticated()
                .anyRequest().permitAll()
            .and()
                .formLogin()
            .and()
                .logout()
        ;

        return http.build();
    }
}
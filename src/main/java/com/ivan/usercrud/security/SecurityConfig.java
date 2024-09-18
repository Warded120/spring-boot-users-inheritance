package com.ivan.usercrud.security;

import com.ivan.usercrud.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
        http
                .authorizeHttpRequests(configurer -> configurer
                    .requestMatchers("/register/**").permitAll()
                    .requestMatchers("/login").permitAll()

                    .requestMatchers("/products/product").hasRole("CUSTOMER")
                    .requestMatchers("/products/add").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/products/update/**").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/products/delete/**").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/products/purchase/**").hasRole("CUSTOMER")
                    .requestMatchers("/products/main").hasRole("CUSTOMER")

                    .requestMatchers("/users/user").hasRole("CUSTOMER")
                    .requestMatchers("/users/user/order-history").hasRole("CUSTOMER")
                    .requestMatchers("/users/delete").hasRole("CUSTOMER")
                    .requestMatchers("/users/delete/**").hasRole("ADMIN")
                    .requestMatchers("/users/update").hasRole("CUSTOMER")
                    .requestMatchers("/users/update/**").hasRole("ADMIN")
                    .requestMatchers("/users/add").hasRole("ADMIN")
                    .requestMatchers("/users/**").hasRole("CUSTOMER")


                    .requestMatchers("/orders/**").hasRole("CUSTOMER")
                    .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll()
                )

                .logout(logout -> logout.permitAll())

                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied")
        );

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}

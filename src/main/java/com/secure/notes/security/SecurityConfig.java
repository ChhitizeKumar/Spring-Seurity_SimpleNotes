package com.secure.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) ->
//                requests.requestMatchers("/contact").permitAll()
//                        .requestMatchers("/public/**").permitAll()
//                        .requestMatchers("/admin/").denyAll()
//                        .anyRequest().authenticated());
////        http.formLogin(withDefaults());
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.httpBasic(withDefaults());
//        return http.build();
//    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.anyRequest().authenticated());
        //if not disable CSRF token is required for authentication
        http.csrf(AbstractHttpConfigurer::disable);
//        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager =
//                new InMemoryUserDetailsManager();
//        if (!manager.userExists("user1")) {
//            manager.createUser(
//                    User.withUsername("user1")
//                            .password("{noop}password1") //noop->not performing any encryption
//                            .roles("USER")
//                            .build()
//            );
//        }
//        if (!manager.userExists("admin")) {
//            manager.createUser(
//                    User.withUsername("admin")
//                            .password("{noop}adminPass")
//                            .roles("ADMIN")
//                            .build()
//            );
//        }
//        return manager;
//    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager =
                new JdbcUserDetailsManager(dataSource);
        if (!manager.userExists("user1")) {
            manager.createUser(
                    User.withUsername("user1")
                            .password("{noop}password1") //noop->not performing any encryption
                            .roles("USER")
                            .build()
            );
        }
        if (!manager.userExists("admin")) {
            manager.createUser(
                    User.withUsername("admin")
                            .password("{noop}adminPass")
                            .roles("ADMIN")
                            .build()
            );
        }
        return manager;
    }

}

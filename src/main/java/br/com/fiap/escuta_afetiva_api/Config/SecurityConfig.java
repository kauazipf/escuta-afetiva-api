package br.com.fiap.escuta_afetiva_api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/pacientes/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/consultas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/user/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
            User.withUsername("edeliz").password("$2a$12$r3B.XzQX43dur19prADf3uMpt0SyOt5Cvl84A1JPB/ODMjHVx6Zn2").roles("ADMIN").build(),
            User.withUsername("kaua").password("$2a$12$my3rVz0UR0iAnUU6J.ZT/OxsMQ2TtcKhkS7cytjxZd/cUY/.kIiv2").roles("USER").build()
        );
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
     
 }
    


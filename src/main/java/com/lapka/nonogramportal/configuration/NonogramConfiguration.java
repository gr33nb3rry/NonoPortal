package com.lapka.nonogramportal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class NonogramConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults());

        return security.build();
    }

    // TODO: This CORS configuration only for development purposes only.
    // TODO: Please change it, if we would host the project remotely.
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        List<String> wildcardList = List.of("*");

        configuration.setAllowedOrigins(wildcardList);
        configuration.setAllowedMethods(wildcardList);
        configuration.setAllowedHeaders(wildcardList);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

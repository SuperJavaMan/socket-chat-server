package com.example.socketchat.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * @author Oleg Pavlyukov
 * on 19.01.2020
 * cpabox777@gmail.com
 */
@Configuration
public class CorsFilterConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
        config.addAllowedOrigin("http://localhost:4200");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.setAllowedMethods(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        source.registerCorsConfiguration("/actuator/**", config);
        source.registerCorsConfiguration("/parse/**", config);
        source.registerCorsConfiguration("/integration/**", config);
        source.registerCorsConfiguration("/mqmdm/**", config);

        return new CorsFilter(source);
    }

}

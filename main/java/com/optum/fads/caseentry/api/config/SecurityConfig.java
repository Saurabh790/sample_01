package com.optum.fads.caseentry.api.config;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Value;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.optum.fads.caseentry.api.security.UserJwtAuthenticationConverter;
import com.optum.fads.caseentry.api.service.IUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final IUserDetailsService userDetailsService;
    
    @Value("${spring.security.oauth2.resourceserver.jwt.unique-id-claims}")
	private List<String> uniqueIdClaims;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig(IUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers("/actuator/**", "/api/**")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .decoder(jwtDecoder())
                                .jwtAuthenticationConverter(userJwtAuthenticationConverter())
                        )
                );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Custom JWT decoder that skips signature validation
        return token -> {
            try {
                JWT jwt = JWTParser.parse(token);

                // Convert claims, handling Date to Instant conversion
                Map<String, Object> claims = new HashMap<>(jwt.getJWTClaimsSet().getClaims());

                convertDateToInstant(claims, "iat");  // issued at
                convertDateToInstant(claims, "exp");  // expires at
                convertDateToInstant(claims, "nbf");  // not before
                convertDateToInstant(claims, "auth_time");  // authentication time

                return Jwt.withTokenValue(token)
                        .headers(headers -> headers.putAll(jwt.getHeader().toJSONObject()))
                        .claims(claimsMap -> claimsMap.putAll(claims))
                        .build();
            } catch (Exception e) {
                throw new JwtException("Failed to parse JWT", e);
            }
        };
    }

    private void convertDateToInstant(Map<String, Object> claims, String claimName) {
        Object value = claims.get(claimName);
        if (value instanceof Date) {
            claims.put(claimName, ((Date) value).toInstant());
        }
    }

    @Bean
    UserJwtAuthenticationConverter userJwtAuthenticationConverter() {
        logger.debug("Creating UserJwtAuthenticationConverter bean");
        return new UserJwtAuthenticationConverter(userDetailsService, uniqueIdClaims);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        logger.debug("Creating PasswordEncoder bean using DelegatingPasswordEncoder");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        logger.debug("Creating CorsConfigurationSource bean");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList(CorsConfiguration.ALL));
        configuration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
        configuration.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
        //	configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        logger.info("CORS configuration registered for all paths");
        return source;
    }
}

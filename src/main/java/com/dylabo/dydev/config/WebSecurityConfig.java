package com.dylabo.dydev.config;

import com.dylabo.dydev.common.handler.AuthEntryPoint;
import com.dylabo.dydev.common.handler.DeniedHandler;
import com.dylabo.dydev.domain.user.enums.UserTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthEntryPoint authEntryPoint;
    private final DeniedHandler deniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // setting
        http
                .cors(configurer -> corsConfigurationSource())
                .csrf(AbstractHttpConfigurer::disable)
//                .csrf(csrf ->
//                        csrf
//                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )

                .sessionManagement(config ->
                        config
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션이 필요하면 생성하도록 세팅
                                .maximumSessions(1)
                )

                // auth
                .authorizeHttpRequests(request ->
                        request
                                // auth
                                .requestMatchers("/auth/sign-in", "/session/user")
                                .permitAll()

                                // system
                                .requestMatchers("/system/**")
                                .hasAnyRole(UserTypes.SUPER.getKey())

                                // admin
                                .requestMatchers("/admin/**")
                                .hasAnyRole(UserTypes.ADMIN.getKey(), UserTypes.SUPER.getKey())

                                // common
                                .requestMatchers("/common/**")
                                .permitAll()

                                // any request
                                .anyRequest()
                                .authenticated()
                )

                .exceptionHandling(config ->
                        config
                                .authenticationEntryPoint(authEntryPoint)
                                .accessDeniedHandler(deniedHandler));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://dylabo.me", "https://www.dylabo.me"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}

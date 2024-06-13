package com.dylabo.dydev.config;

import com.dylabo.dydev.common.filter.AuthProcessingFilter;
import com.dylabo.dydev.common.filter.ExceptionHandlerFilter;
import com.dylabo.dydev.common.handler.AuthEntryPoint;
import com.dylabo.dydev.common.handler.AuthSuccessHandler;
import com.dylabo.dydev.common.handler.DeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
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

    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthEntryPoint authEntryPoint;
    private final DeniedHandler deniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // setting
        http
                .cors(configurer -> corsConfigurationSource())
                .csrf(AbstractHttpConfigurer::disable)

                // admin need auth
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/admin*").authenticated()
                )

                // permit all
                .authorizeHttpRequests(request ->
                        request
                                .anyRequest().permitAll()
                )
//                .formLogin(config ->
//                        config
//                                .loginProcessingUrl("/auth/sign-in")
//                                .usernameParameter("userId")
//                                .successHandler(new LoginSuccessHandler())
//                )
//                .logout(config -> config.logoutUrl("/auth/sign-out"))

                .addFilterBefore(new ExceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(config ->
                        config
                                .authenticationEntryPoint(authEntryPoint)
                                .accessDeniedHandler(deniedHandler));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://dylabo.me"));
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

    @Bean
    public AuthProcessingFilter authFilter() throws Exception {
        AuthProcessingFilter authProcessingFilter = new AuthProcessingFilter();
        authProcessingFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        authProcessingFilter.setAuthenticationSuccessHandler(authSuccessHandler);

        // REST API 사용을 위해 추가
        authProcessingFilter.setSecurityContextRepository(
                new DelegatingSecurityContextRepository(
                        new RequestAttributeSecurityContextRepository(),
                        new HttpSessionSecurityContextRepository()
                ));

        return authProcessingFilter;
    }

}

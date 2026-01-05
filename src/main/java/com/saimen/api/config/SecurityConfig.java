package com.saimen.api.config;

import com.saimen.api.services.CustomUserDetailsService;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Auth Provider
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // 3. Filter Chain (Aturan Satpam)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable()) // Matikan CSRF untuk kemudahan development

                // ATURAN AKSES URL
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // HALAMAN PUBLIC (Login & Static Files) - WAJIB PERMIT ALL
                        .requestMatchers("/index.html", "/", "/javaScript/**", "/style.css", "/images/**")
                        .permitAll()

                        // SISANYA WAJIB LOGIN
                        .anyRequest().authenticated())

                // SETTING FORM LOGIN
                .formLogin(form -> form
                        .loginPage("/index.html") // URL Halaman Login (HTML kita)
                        .loginProcessingUrl("/perform_login") // URL Virtual untuk Submit Form
                        .defaultSuccessUrl("/landing.html", true) // KALO SUKSES KE SINI (PENTING!)
                        .failureUrl("/index.html?error=true")
                        .permitAll())

                // SETTING LOGOUT
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/index.html") // Balik ke login setelah logout
                        .permitAll());

        return http.build();
    }

    // 3. PENTING: Definisi Aturan CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Masukkan URL Frontend Anda (VS Code Live Server)
        // Perhatikan: http://127.0.0.1:5500 BEDANYA dengan http://localhost:5500
        // Masukkan keduanya agar aman.
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500", "http://localhost:5500"));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Izinkan kirim Cookie/Session
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
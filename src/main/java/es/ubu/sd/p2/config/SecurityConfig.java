package es.ubu.sd.p2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración global de seguridad de Spring Security.
 * <p>
 * Define las reglas de autenticación, autorización de rutas HTTP, cifrado de contraseñas y la integración con el
 * formulario de login personalizado.
 * </p>
 *
 * @author José Luis Petisco Rodríguez
 * @version 1.0
 */
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Inyección del servicio personalizado para la carga de usuarios desde la base de datos.
     */
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Define el bean para el cifrado de contraseñas usando el algoritmo BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Expone el gestor de autenticación principal de Spring Security.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura la cadena de filtros de seguridad (Security Filter Chain).
     * Establece los permisos por ruta y configura el formulario de acceso.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(customUserDetailsService)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Utiliza nuestro HTML personalizado
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
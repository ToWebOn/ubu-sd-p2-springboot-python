package es.ubu.sd.p2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase de configuración principal de Spring Boot.
 * <p>
 * Se encarga de definir e inicializar los beans de contexto general que serán inyectados en otros componentes de la
 * aplicación.
 * </p>
 * * @author José Luis Petisco Rodríguez
 * @version 1.0
 */
@Configuration
public class AppConfig {

    /**
     * Define el cliente HTTP síncrono para el consumo de servicios web.
     * <p>
     * Se inyectará en los servicios que necesiten comunicarse con sistemas externos, específicamente para enlazar
     * este backend con la API externa desarrollada en Python (Flask) para la generación de errores.
     * </p>
     * * @return una nueva instancia de {@link RestTemplate} gestionada por el contenedor de Spring.
     */
    @Bean
    public RestTemplate restTemplate() {
        // Retorna el cliente HTTP por defecto sin interceptores adicionales en este punto
        return new RestTemplate();
    }
}
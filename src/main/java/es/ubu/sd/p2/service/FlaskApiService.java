package es.ubu.sd.p2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Servicio encargado de la comunicación HTTP con la API externa de Python (Flask).
 * <p>
 * Actúa como cliente para invocar los endpoints de simulación de errores, aprovechando la resolución de nombres DNS de
 * Docker para comunicarse a través de la red interna de contenedores.
 * </p>
 *
 * @author José Luis Petisco Rodríguez
 * @version 1.0
 */
@Service
public class FlaskApiService {

    private final RestTemplate restTemplate;

    // Usamos el nombre del servicio de Docker (flask-api) en lugar de localhost
    private final String FLASK_API_URL = "http://flask-api:5000/api";

    /**
     * Inyecta el cliente HTTP configurado en el contexto de Spring.
     *
     * @param restTemplate Cliente REST proporcionado por la configuración de la aplicación.
     */
    public FlaskApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Invoca el endpoint de Flask diseñado para fallar por conexión a base de datos.
     *
     * @return La respuesta de la API (no se llegará a retornar en caso de error 500).
     * @throws org.springframework.web.client.HttpServerErrorException Si Flask responde con estado 500.
     */
    public String simularErrorBaseDatos() {
        // Esto provocará un error 500 en Flask que Spring Boot capturará
        return restTemplate.getForObject(FLASK_API_URL + "/error-db", String.class);
    }

    /**
     * Invoca el endpoint de Flask diseñado para fallar por lectura de archivos inexistentes.
     *
     * @return La respuesta de la API (no se llegará a retornar en caso de error 500).
     * @throws org.springframework.web.client.HttpServerErrorException Si Flask responde con estado 500.
     */
    public String simularErrorArchivos() {
        // Esto provocará un error 500 en Flask que Spring Boot capturará
        return restTemplate.getForObject(FLASK_API_URL + "/error-file", String.class);
    }
}
package es.ubu.sd.p2.controller;

import es.ubu.sd.p2.service.FlaskApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador principal de la aplicación web.
 * <p>
 * Gestiona la navegación de las vistas de la interfaz de usuario y expone los endpoints que actúan como puente para
 * invocar la API externa (Flask), permitiendo desencadenar las simulaciones de error desde el frontend.
 * </p>
 *
 * @author José Luis Petisco Rodríguez
 * @version 1.0
 */
@Controller
public class MainController {

    /**
     * Inyección del servicio encargado de la comunicación con la API de Python.
     */
    @Autowired
    private FlaskApiService flaskApiService;

    /**
     * Renderiza la página de inicio de la aplicación.
     *
     * @param model Modelo de Spring UI (opcional para esta vista plana).
     * @return El nombre de la plantilla Thymeleaf "index".
     */
    @GetMapping("/")
    public String vistaHome(Model model){
        return "index";
    }

    /**
     * Renderiza la vista de autenticación.
     *
     * @return El nombre de la plantilla Thymeleaf "login".
     */
    @GetMapping("/login")
    public String vistaLogin() {
        return "login";
    }

    /**
     * Renderiza el panel de control para lanzar las peticiones a la API.
     *
     * @return El nombre de la plantilla Thymeleaf "simulacion".
     */
    @GetMapping("/simulacion")
    public String vistaSimulacion() {
        return "simulacion";
    }

    /**
     * Intercepta la petición HTTP para simular un fallo de base de datos en Python.
     * <p>
     * Nota: Se espera que esta llamada lance una excepción capturable por el gestor global, por lo que el retorno
     * normal rara vez se ejecutará.
     * </p>
     *
     * @param model Modelo para inyectar la respuesta (si fuera exitosa) en la vista.
     * @return El nombre de la vista "simulacion".
     */
    @GetMapping("/simular-error-db")
    public String simularErrorDb(Model model) {
        String respuesta = flaskApiService.simularErrorBaseDatos();
        model.addAttribute("mensaje", respuesta);
        // Si la llamada no lanza la excepción 500 (que debería lanzarla), recargamos la página
        return "simulacion";
    }

    /**
     * Intercepta la petición HTTP para simular un fallo de lectura de archivos en Python.
     *
     * @param model Modelo para inyectar la respuesta (si fuera exitosa) en la vista.
     * @return El nombre de la vista "simulacion".
     */
    @GetMapping("/simular-error-archivo")
    public String simularErrorArchivo(Model model) {
        String respuesta = flaskApiService.simularErrorArchivos();
        model.addAttribute("mensaje", respuesta);
        // Si la llamada no lanza la excepción 500 (que debería lanzarla), recargamos la página
        return "simulacion";
    }
}
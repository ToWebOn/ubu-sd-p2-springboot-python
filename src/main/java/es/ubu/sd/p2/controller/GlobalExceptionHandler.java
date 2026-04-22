package es.ubu.sd.p2.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.dao.DataAccessException;

/**
 * Controlador global para la gestión de excepciones.
 * <p>
 * Centraliza la captura de errores en toda la aplicación, transformando excepciones técnicas en respuestas visuales
 * amigables para el usuario.
 * </p>
 * * @author José Luis Petisco Rodríguez
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestiona los fallos de comunicación con servicios REST externos.
     * * @param ex La excepción de cliente REST capturada.
     * @param model El modelo para la interfaz de usuario.
     * @return El nombre de la vista de error.
     */
    @ExceptionHandler(RestClientException.class)
    public String handleRestClientException(RestClientException ex, Model model) {
        model.addAttribute("errorTitulo", "Error de Comunicación con API Externa");
        model.addAttribute("errorMensaje", "No hemos podido contactar correctamente con el " +
                "servicio externo de Python. Es posible que el recurso solicitado no esté disponible.");
        model.addAttribute("detalle", ex.getMessage());
        return "error-page";
    }

    /**
     * Gestiona las excepciones relacionadas con la persistencia de datos.
     * * @param ex La excepción de acceso a datos capturada.
     * @param model El modelo para la interfaz de usuario.
     * @return El nombre de la vista de error.
     */
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseExceptions(DataAccessException ex, Model model) {
        model.addAttribute("errorTitulo", "Error de Acceso a Base de Datos");
        model.addAttribute("errorMensaje", "Se ha producido un problema al intentar leer o " +
                "escribir información en nuestra base de datos. Por favor, inténtelo más tarde.");
        model.addAttribute("detalle", ex.getMessage());
        return "error-page";
    }

    /**
     * Captura cualquier excepción no controlada específicamente por otros manejadores.
     * * @param ex La excepción genérica capturada.
     * @param model El modelo para la interfaz de usuario.
     * @return El nombre de la vista de error.
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("errorTitulo", "Error Inesperado del Sistema");
        model.addAttribute("errorMensaje", "Ha ocurrido un error inesperado en el servidor. " +
                "Nuestro equipo técnico ha sido notificado.");
        model.addAttribute("detalle", ex.getMessage());
        return "error-page";
    }
}
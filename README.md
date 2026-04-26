# Práctica Obligatoria 2 - Sistemas Distribuidos

Este proyecto implementa un sistema de gestión de excepciones distribuidas conectando un frontend/backend en Java (Spring Boot) con un API REST de terceros simulada en Python (Flask), todo orquestado mediante contenedores Docker.

## Arquitectura y Tecnologías
*   **Backend y Frontend:** Java (JDK 25), Spring Boot 4.0.3, Spring Security, JPA, Hibernate, Thymeleaf.
*   **API de Terceros:** Python 3.11, Flask.
*   **Bases de Datos:** MySQL 8 (Gestión de usuarios Java) y PostgreSQL 12 (Simulación de errores Python).
*   **Infraestructura:** Docker y Docker Compose.

## Puertos Configurados
*   Aplicación Web (Spring Boot): `http://localhost:7001`
*   API Python (Flask): `http://localhost:5000`

## Instrucciones de Ejecución
1. Clonar el repositorio.
2. Ejecutar en la raíz del proyecto el comando:
   `docker-compose up -d --build`
3. Esperar a que Maven descargue las dependencias y recompile el proyecto.
4. Acceder en el navegador a `http://localhost:7001`.
5. **Credenciales por defecto:**
    * Usuario: `admin`
    * Contraseña: `admin`

## Características
El sistema simula y captura excepciones críticas (como caídas de base de datos o fallos de lectura de archivos en el API de Python), interceptándolas con un `@ControllerAdvice` en Spring Boot para mostrar al usuario una interfaz amigable y traducida en lugar de una traza de error genérica.


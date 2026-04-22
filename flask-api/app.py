"""
API REST en Flask para la simulación de errores.
Módulo diseñado para forzar excepciones controladas (Base de Datos y E/S de archivos) que posteriormente serán
interceptadas y gestionadas por el backend en Spring Boot.
"""

from flask import Flask, jsonify
import psycopg2
import os

app = Flask(__name__)

@app.route('/api/error-db', methods=['GET'])
def error_db():
    """
    Simula una excepción de conexión a base de datos.
    Intenta establecer conexión con PostgreSQL utilizando credenciales y nombres de base de datos intencionadamente
    incorrectos.
    Retorna:
        tuple: Objeto JSON con el mensaje de error y el detalle técnico, junto con el código de estado HTTP 500
               (Internal Server Error).
    """
    try:
        # Intentamos conectar a PostgreSQL con datos erróneos a propósito
        conn = psycopg2.connect(
            dbname="wrong_db",
            user="wrong_user",
            password="wrong_password",
            host="postgres-db",
            port="5432"
        )
    except Exception as e:
        return jsonify({
            "error": "Error de acceso a Base de Datos en Python",
            "detalle": str(e)
        }), 500

@app.route('/api/error-file', methods=['GET'])
def error_file():
    """
    Simula una excepción de lectura de archivos (Error de E/S).
    Intenta abrir un archivo de texto que no existe en el sistema de archivos del contenedor.

    Retorna:
        tuple: Objeto JSON con el mensaje de error y el detalle técnico, 
               junto con el código de estado HTTP 500.
    """
    try:
        # Intentamos abrir un archivo inexistente
        with open('archivo_inexistente_de_configuracion.txt', 'r') as f:
            data = f.read()
    except Exception as e:
        return jsonify({
            "error": "Error de E/S de Archivos en Python",
            "detalle": str(e)
        }), 500

@app.route('/api/health', methods=['GET'])
def health():
    """
    Endpoint de comprobación de estado (Healthcheck).
    Permite verificar si el contenedor Docker ha levantado la API correctamente.

    Retorna:
        tuple: Objeto JSON con el estado de la API y código HTTP 200 (OK).
    """
    return jsonify({"status": "API de Python funcionando correctamente"}), 200

if __name__ == '__main__':
    # Arrancamos en el puerto 5000 accesible desde cualquier IP del contenedor
    app.run(host='0.0.0.0', port=5000)
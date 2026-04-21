from flask import Flask, jsonify
import psycopg2
import os

app = Flask(__name__)

# Endpoint 1: Simula una excepción de base de datos
@app.route('/api/error-db', methods=['GET'])
def error_db():
    try:
        # Intentamos conectar a PostgreSQL con datos erróneos a propósito
        conn = psycopg2.connect(
            dbname="wrong_db",
            user="wrong_user",
            password="wrong_password",
            host="postgres", # Este será el nombre del servicio en docker-compose
            port="5432"
        )
    except Exception as e:
        # Devolvemos un código HTTP 500 (Internal Server Error) y el detalle
        return jsonify({"error": "Error de acceso a Base de Datos en Python", "detalle": str(e)}), 500

# Endpoint 2: Simula una excepción de lectura de archivos
@app.route('/api/error-file', methods=['GET'])
def error_file():
    try:
        # Intentamos abrir un archivo que no existe
        with open('archivo_inexistente_de_configuracion.txt', 'r') as f:
            data = f.read()
    except Exception as e:
        # Devolvemos un código HTTP 500
        return jsonify({"error": "Error de E/S de Archivos en Python", "detalle": str(e)}), 500

@app.route('/api/health', methods=['GET'])
def health():
    return jsonify({"status": "API de Python funcionando correctamente"}), 200

if __name__ == '__main__':
    # Arrancamos en el puerto 5000 accesible desde cualquier IP del contenedor
    app.run(host='0.0.0.0', port=5000)

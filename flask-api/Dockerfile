# Usamos una imagen ligera de Python
FROM python:3.11-slim

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos las dependencias y las instalamos
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copiamos el código de la API
COPY app.py .

# Exponemos el puerto de Flask
EXPOSE 5000

# Comando para ejecutar la aplicación
CMD ["python", "app.py"]
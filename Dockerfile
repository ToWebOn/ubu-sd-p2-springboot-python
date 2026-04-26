FROM maven:3.9-amazoncorretto

# Creación de grupo y usuario sin privilegios para aislar la ejecución
RUN groupadd -r nonroot && useradd -r -g nonroot -m nonroot \
    && mkdir -p /app \
    && chown -R nonroot:nonroot /app

WORKDIR /app

# Copia del código fuente al contexto de construcción
COPY . .

# Compilación del empaquetado omitiendo la fase de pruebas
RUN mvn clean install -DskipTests

# Reasignación de permisos tras la generación del directorio /target por el usuario root
RUN chown -R nonroot:nonroot /app

# Transición a usuario sin privilegios para el arranque del servicio
USER nonroot

CMD ["mvn", "spring-boot:run"]
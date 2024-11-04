# Usa una imagen base de Maven para construir el proyecto
FROM maven:3.9.0-eclipse-temurin-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usa una imagen ligera de JDK para ejecutar el jar
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /app/target/ecommercespringboot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

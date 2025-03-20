FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/servicio-publicaciones-1.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/servicio-usuarios-1.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
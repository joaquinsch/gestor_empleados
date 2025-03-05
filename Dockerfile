FROM openjdk:17-jdk-slim
ARG JAR-FILE=target/gestor_empleados-0.0.1.jar
COPY ${JAR_FILE} app_gestor_empleados.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_gestor_empleados.jar"]
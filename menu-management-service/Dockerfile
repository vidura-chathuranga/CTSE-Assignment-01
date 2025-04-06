FROM eclipse-temurin:17-jdk-alpine

COPY target/menu-management-service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
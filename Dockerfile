FROM eclipse-temurin:21-jre

WORKDIR /app

RUN groupadd spring && useradd spring -g spring

# Забираем jar-файл из локальной папки сборки Gradle
COPY build/libs/*.jar app.jar

RUN chown spring:spring app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
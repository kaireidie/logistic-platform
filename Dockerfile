# ---------- Stage 1: Build ----------
FROM gradle:9.1.0-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle bootJar --no-daemon

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
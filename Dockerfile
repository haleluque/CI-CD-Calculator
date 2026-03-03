# Build stage: Maven + JDK 17
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copy dependency manifests first for better layer caching
COPY pom.xml .
COPY checkstyle.xml .

# Download dependencies (cached unless pom.xml changes)
RUN mvn -B dependency:go-offline -DskipTests

# Copy source and build
COPY src ./src
RUN mvn -B package -DskipTests -q

# Run stage: slim JRE
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Non-root user for security
RUN addgroup -g 1000 app && adduser -u 1000 -G app -D app
USER app

# Copy the built JAR from builder (Spring Boot default name from pom)
COPY --from=builder /build/target/Calculator-*.jar app.jar

# Render and similar PaaS set PORT; application.properties uses server.port=${PORT:8080}
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

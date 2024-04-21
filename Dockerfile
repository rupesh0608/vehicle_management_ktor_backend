# Build stage
FROM ubuntu:latest AS build

# Set working directory
WORKDIR /app

# Update and install JDK
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy source code
COPY . .

# Build fat jar
RUN ./gradlew runFatJar

# Runtime stage
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Expose port
EXPOSE 8080

# Copy jar from build stage
COPY --from=build /app/build/libs/app.jar app.jar

# Set entry point
ENTRYPOINT ["java", "-jar", "app.jar"]

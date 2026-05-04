# STAGE 1: Build
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copy gradle files first to leverage Docker cache
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Give execution permission and download dependencies
RUN chmod +x ./gradlew
RUN ./gradlew dependencies

# Copy source code and build the JAR
COPY src src
RUN ./gradlew bootJar -x test

# STAGE 2: Run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
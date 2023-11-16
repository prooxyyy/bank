# Stage 1: Build the application
FROM maven:3.9.3-amazoncorretto-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv6Addresses=false

# Stage 2: Create the final image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8189
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
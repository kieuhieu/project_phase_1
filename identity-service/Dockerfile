# Stage 1: Build
FROM maven:3.9.9-amazoncorretto-21-debian as build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run
FROM amazoncorretto:21.0.6-alpine3.20

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV JAVA_OPTS="-Xmx512m -Xms256m"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]
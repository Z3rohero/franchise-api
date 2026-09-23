FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "\
  : \"${DB_URL:?ERROR: DB_URL no está definida}\" && \
  : \"${DB_USERNAME:?ERROR: DB_USERNAME no está definida}\" && \
  : \"${DB_PASSWORD:?ERROR: DB_PASSWORD no está definida}\" && \
  echo 'Variables de base de datos detectadas correctamente' && \
  exec java -jar app.jar"]

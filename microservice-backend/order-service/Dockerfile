FROM eclipse-temurin:21 AS build

WORKDIR /app

RUN apt update && apt install -y maven

COPY . .

RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 9070

ENTRYPOINT ["java", "-jar", "app.jar"]
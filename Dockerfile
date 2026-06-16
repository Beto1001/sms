FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /project

COPY ./ /project

RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /project/target/*.jar ./app.jar

CMD ["java", "-jar", "./app.jar"]
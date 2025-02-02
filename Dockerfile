FROM openjdk:23-jdk AS build
WORKDIR /app
COPY demo/pom.xml .
COPY demo/src src

COPY demo/mvnw .
COPY demo/.mvn .mvn

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk
VOLUME /tmp

COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080

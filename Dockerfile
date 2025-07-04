FROM openjdk:24-jdk

WORKDIR /app

COPY target/demoSpringBoot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

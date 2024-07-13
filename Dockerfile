FROM openjdk:8-jdk-alpine

EXPOSE 8082

ADD target/Rest-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
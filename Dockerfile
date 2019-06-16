FROM openjdk:latest
ADD target/superHero-api.jar superHero-api.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar","superHero-api.jar"]
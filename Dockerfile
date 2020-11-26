FROM openjdk:13

WORKDIR "/usr/src/app"

COPY [".", "./"]

RUN ./gradlew build

RUN mv ./build/libs/*.jar ./build/libs/app.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "./build/libs/app.jar"]

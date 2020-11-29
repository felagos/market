FROM adoptopenjdk:13-jdk-hotspot as builder

WORKDIR /home/gradle/app

COPY [".", "./"]

RUN ./gradlew build --no-daemon

FROM adoptopenjdk:13-jdk-hotspot

WORKDIR /usr/src/app

COPY --from=builder /home/gradle/app/build/libs/*.jar ./app.jar

EXPOSE $PORT

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar"]
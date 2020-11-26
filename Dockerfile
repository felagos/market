FROM openjdk:13

WORKDIR "/usr/src/app"

ARG PORT
ARG DB_HOST
ARG DB_NAME
ARG DB_USER
ARG DB_PASSWORD
ARG JWT_EXPIRATION
ARG JWT_KEY

ENV PORT=$PORT
ENV DB_HOST=$DB_HOST
ENV DB_NAME=$DB_NAME
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
ENV JWT_EXPIRATION=$JWT_EXPIRATION
ENV JWT_KEY=$JWT_KEY

COPY [".", "./"]

RUN ./gradlew build

RUN mv ./build/libs/*.jar ./build/libs/app.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "./build/libs/app.jar"]

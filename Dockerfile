FROM gradle:4.10.0-jdk8-alpine  AS GRADLE_BUILD

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jre-slim

RUN mkdir /app

COPY --from=GRADLE_BUILD /home/gradle/src/build/libs/*.jar /app/lf-code-challenge-0.1.0.jar
ENTRYPOINT ["java","-jar","/app/lf-code-challenge-0.1.0.jar"]
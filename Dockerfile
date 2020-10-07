FROM gradle:6.5.1-jdk14 AS build
COPY --chown=gradle:gradle . /bruhbot
WORKDIR /bruhbot
RUN gradle shadowJar --no-daemon

FROM openjdk:11.0.8-jre-slim
RUN mkdir /config/
COPY --from=build /bruhbot/build/libs/*.jar /

ENTRYPOINT ["java", "-jar", "/BruhBot.jar"]
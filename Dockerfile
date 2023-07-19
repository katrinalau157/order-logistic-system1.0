#
# Build stage
#
FROM maven:3.9.3-amazoncorretto-17 AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
WORKDIR /usr/app
RUN mvn clean install -Dmaven.test.skip

##
## Package stage
##
FROM openjdk:17-jdk-slim
COPY --from=build /usr/app/target/order-logistic-system1.0-0.0.1-SNAPSHOT.jar /tmp/order-logistic-system1.0-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java $OPTS -jar /tmp/order-logistic-system1.0-0.0.1-SNAPSHOT.jar
EXPOSE 8080
#
# Build stage
#
FROM maven:3.9.3-amazoncorretto-17 AS build

#copy pom
COPY pom.xml .

#resolve maven dependencies
RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target/

#copy source
COPY src ./src

# build the app (no dependency download here)
RUN mvn clean package -Dmaven.test.skip

# split the built app into multiple layers to improve layer rebuild
RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf ../order-logistic-system1.0-0.0.1-SNAPSHOT.jar

#
# Package stage
#
FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/order-logistic-system1.0-0.0.1-SNAPSHOT.jar order-logistic-system1.0-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/order-logistic-system1.0-0.0.1-SNAPSHOT.jar"]
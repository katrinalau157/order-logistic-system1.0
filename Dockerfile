FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/order-logistic-system1.0-0.0.1-SNAPSHOT.jar order-logistic-system1.0-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/order-logistic-system1.0-0.0.1-SNAPSHOT.jar"]
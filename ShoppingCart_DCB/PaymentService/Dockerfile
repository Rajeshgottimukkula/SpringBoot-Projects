FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} paymentservice.jar
ENTRYPOINT ["java","-jar","/paymentservice.jar"]
EXPOSE 8061
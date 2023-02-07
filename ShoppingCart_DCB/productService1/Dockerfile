FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product_service.jar
ENTRYPOINT ["java","-jar","/product_service.jar"]
EXPOSE 8059
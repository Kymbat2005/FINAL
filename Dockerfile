FROM openjdk:21-oracle
MAINTAINER kymbat
COPY backend.jar my-spring-backend.jar
ENTRYPOINT ["java","-jar","my-spring-backend.jar"]
FROM openjdk:17
EXPOSE 8080
COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java","-jar","app.jar"]
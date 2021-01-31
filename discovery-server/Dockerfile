FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

FROM openjdk:latest
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]
#FROM openjdk:latest


#ADD target/discovery-server-0.0.1-SNAPSHOT.jar app.jar
#COPY . /discovery-server/target
#ENTRYPOINT ["java", "-jar", "/discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar" ]

#EXPOSE 8761
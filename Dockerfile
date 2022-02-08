FROM openjdk:11

COPY target/ModelsViewerWeb-0.0.1-SNAPSHOT.jar testApp.jar
EXPOSE 8999

ENTRYPOINT ["java","-jar","/testApp.jar", "-agentlib:jdwp=transport=dt_socket,address=*:8000,server=y,suspend=n"]


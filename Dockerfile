FROM openjdk:11
#RUN apt-get update
#RUN apt-get -y install libxtst6
#RUN apt-get -y install libxrender1

COPY target/ModelsViewerWeb-0.0.1-SNAPSHOT.jar testApp.jar
EXPOSE 8189

ENTRYPOINT ["java","-jar","/testApp.jar", "-agentlib:jdwp=transport=dt_socket,address=*:8000,server=y,suspend=n"]


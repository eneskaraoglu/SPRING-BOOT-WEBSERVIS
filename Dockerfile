FROM openjdk:17
LABEL maintainer="bilisim.com.tr"
ADD target/WebServis-0.0.1-SNAPSHOT.jar erpSpringWebServis.jar
ENTRYPOINT ["java","-jar","erpSpringWebServis.jar"]
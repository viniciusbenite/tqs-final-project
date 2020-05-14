# Start with a base image containing Java runtime
FROM openjdk:11-alpine

# Add Maintainer Info
LABEL maintainer="viniciusribeiro@ua.pt"
VOLUME /tmp
ENV HOME /root
ADD target/tqs_final_project-1.0-SNAPSHOT.jar barbershop/barbershop-app:0.1-SNAPSHOP.jar
RUN sh -c 'touch /barbershop/barbershop-app:0.1-SNAPSHOP.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
EXPOSE 8080 8787
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /barbershop/barbershop-app:0.1-SNAPSHOP" ]
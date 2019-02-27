FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime
ADD simul-spot-server-0.0.1-SNAPSHOT.jar simul-spot-server.jar
ENTRYPOINT ["java","-Xmx1024m","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+08", "-jar","/simul-spot-server.jar"]

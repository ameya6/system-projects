FROM openjdk:11

ENV JAVA_OPTS " -Xms2G -Xmx2G"

EXPOSE 9006

ARG JAR_FILE=target/url-shortner-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

#WORKDIR app

#COPY ./target/spr-dock-1.0-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "app.jar"]
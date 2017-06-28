from openjdk:8-jre
MAINTAINER Peter Coulton <petercoulton@gmail.com>
EXPOSE 8080
VOLUME /tmp
ARG JAR_NAME
ARG VERSION
ADD ${JAR_NAME}-${VERSION}.jar /usr/local/bin/bluecowmoon.jar
RUN sh -c "touch /usr/local/bin/bluecowmoon.jar"
ENTRYPOINT ["java", "-jar", "/usr/local/bin/bluecowmoon.jar"]

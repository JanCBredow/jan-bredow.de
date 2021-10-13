FROM openjdk:16
MAINTAINER <your_mail@tld.io>

WORKDIR /usr/src/app

COPY webserver-demo-0.0.1-SNAPSHOT.jar webserver-demo-0.0.1-SNAPSHOT.jar
COPY config.json config.json
COPY banner.txt banner.txt
COPY application.properties application.properties
COPY static static
COPY templates templates
COPY keystore.p12 keystore.p12

EXPOSE 8443

ENTRYPOINT [ "java", "-jar", "webserver-demo-0.0.1-SNAPSHOT.jar"]

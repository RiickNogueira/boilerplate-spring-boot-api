FROM openjdk:11-jre-stretch

# oncletom.io/2015/docker-encoding
ENV LANG C.UTF-8

WORKDIR /app
COPY build/libs/name-api-*.jar app.jar

ENTRYPOINT exec java \
$JAVA_OPTS \
-Doracle.jdbc.timezoneAsRegion=false \
-Djava.security.egd=file:/dev/./urandom \
-jar app.jar \
$APP_ARGS

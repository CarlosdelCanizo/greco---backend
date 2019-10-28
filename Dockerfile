FROM openjdk:8
WORKDIR /usr/src/greco
ADD ./build/libs/greco-0.0.1-SNAPSHOT.jar greco-0.0.1-SNAPSHOT.jar
EXPOSE 8082
CMD java -jar -Dspring.profiles.active=dev -Dserver.port=8082 greco-0.0.1-SNAPSHOT.jar

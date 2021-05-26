FROM openjdk:11
ADD target/banktracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar banktracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /banktracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar"]
FROM java:latest
ADD target/departure-times-api-1.0-SNAPSHOT.jar /exec/departure-times-api.jar
ADD service.yml /config/service.yml
ADD example.keystore example.keystore
CMD java -jar /exec/departure-times-api.jar server /config/service.yml
EXPOSE 8080
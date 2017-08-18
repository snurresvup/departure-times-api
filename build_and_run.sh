mvn clean
mvn package
docker build --no-cache -t departure-times-api:latest .
docker stop departure-times-api
docker rm departure-times-api
docker run --name departure-times-api -p 8080:8080 -p 8081:8081 -ti -d departure-times-api
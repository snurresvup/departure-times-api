mvn clean
mvn package
docker build --no-cache -t departure-times-api:latest .
docker rm -f departure-times-api
docker run --restart always --name departure-times-api --link mongodb:mongodb -p 8080:8080 -p 8081:8081 -tid departure-times-api

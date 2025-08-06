# spring-lotto-router-handler

## Git
```
git clone https://github.com/chiwoo-samples/spring-lotto-router-handler.git

cd spring-lotto-router-handler

git config --local user.name <YOUR_NAME>
git config --local user.email <YOUR_EMAIL>
git config --local --list
```

## Generate Maven Wrapper
```
mvn -N io.takari:maven:wrapper
```


## Build
```
./mvnw clean package -DskipTests=true
```

## Run
```
./mvnw -DskipTests=true spring-boot:run
```

## Run with jar
```
java -jar target/lotto-service.jar
```

## Build Image
```
docker build -t "symplesims/lotto-service:1.0.0" -f ./docker/Dockerfile-local .
```

## Run Docker Container

```
docker run --rm --name lotto-service -p 8080:8080 symplesims/lotto-service:1.0.0
```

## Appendix

### health check

```
curl --location -X GET 'http://localhost:8080/actuator/health'
```

### generate lotto-645

```
curl --location -X GET 'http://localhost:8080/api/lotto/lucky' -H 'Content-Type: application/json'
```

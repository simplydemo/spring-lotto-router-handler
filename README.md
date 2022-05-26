# spring-lotto-router-handler

## Git
```
git clone https://github.com/chiwoo-samples/spring-lotto-router-handler.git

cd spring-lotto-router-handler

git config --local user.name <YOUR_NAME>
git config --local user.email <YOUR_EMAIL>
git config --local --list
```

## Build
```
mvn clean package -DskipTests=true
```

## Run
```
mvn -DskipTests=true spring-boot:run
```

## Run with jar
```
java -jar target/lotto-service.jar
```

## Build Image
```
docker build -t "symplesims/lotto-service:0.0.1" -f ./docker/Dockerfile .
```

## Run Docker Container
```
docker run -d --name lotto-service --publish "0.0.0.0:8080:8080" symplesims/lotto-service:0.0.1
```


## Appendix

### check with cURL
```
curl --location -X GET 'http://localhost:8080/api/lotto/lucky' -H 'Content-Type: application/json'
```

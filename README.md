# Employee-Ms-Grpc
## Employee microservices application using gRPC

## Services

| Service Number | Project Name
| ------ | ------ |
| Service 1 | employee-bff-ms
| Service 2 | employee-api-ms

## Features

- Store data in CSV or XML file format.
- Update existing data.
- Read and download the saved files.

## Pre-requisites

- JDK 11
- Maven 3.2+
- Eclipse IDE (optional)
- Postman (optional)

## Execution steps

### 1. Get the Source Code
```
git clone https://github.com/Debdyut/Employee-Ms-Grpc.git
cd Employee-Ms-Grpc
```
### 2. Run service 2
```
cd employee-api-ms
./mvnw spring-boot:run
```
### 3. Run service 1 from a new terminal
```
cd employee-bff-ms
./mvnw spring-boot:run
```
### 4. Open Swagger-UI
[http://localhost:8081/employeeManagement/swagger-ui.html](http://localhost:8081/employeeManagement/swagger-ui.html)
### 5. Import collection in Postman
Import the provided collection in postman to test the APIs
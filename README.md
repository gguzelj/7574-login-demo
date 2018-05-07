# 7574-login-demo
Login api demo

## Requisites

This applications runs with java 8, and requires a local mysql service running.
Connection settings are placed under application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/login
spring.datasource.username=root
```

## Docker

Run:

```bash
    git clone https://github.com/gguzelj/7574-login-demo.git
    cd 7574-login-demo
    docker-compose up
```

## Download, build and run

In order to build the project execute:

```bash
    git clone https://github.com/gguzelj/7574-login-demo.git
    cd 7574-login-demo
    mvn clean package
    java -jar presentation/target/presentation-0.0.1-SNAPSHOT.jar
```

## Endpoints



### Login

This endpoint performs the login.

**Request** 
```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"email":"foo@bar.com.ar","password":"qwerty"}' \
  http://localhost:8080/login
```

**Response**

```json
{  
   "value":"eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwiZXhwIjoxNTI1NjY2OTQ5LCJpYXQiOjE1MjU2NjMzNDk0NTMsImVtYWlsIjoiZ2VybWFuLmd1emVsakBmaXViYS5jb20uYXIifQ.GgcaDT52_lKg-tuWNjklsKH9exAiSZL7uZxWjP88l78"
}
```

### Create User

Creates a new user in the application

**Request**

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"email":"foo@bar.com.ar","password":"qwerty"}' \
  http://localhost:8080/users
```

**Response**

```json
{  
   "id":1,
   "email":"foo@bar.com.ar",
   "password":"b1b3773a05c0ed0176787a4f1574ff0075f7521e"
}
```

### Read all Users

This endpoint requires authentication. Just use the login response and add it in the 'Authorization' header:

**Request**

```bash
curl --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwiZXhwIjoxNTI1NjY3NDg1LCJpYXQiOjE1MjU2NjM4ODU3MzUsImVtYWlsIjoiZ2VybWFuLmd1emVsakBmaXViYS5jb20uYXIifQ.Sim-vGFvWe_BZRyj30EPIlt4l-oK647AqSaqexEOYbU" \ 
http://localhost:8080/users
```

**Response**

```json
[  
   {  
      "id":1,
      "email":"test@fiuba.com.ar",
      "password":"b1b3773a05c0ed0176787a4f1574ff0075f7521e"
   },
   {  
      "id":2,
      "email":"foo@bar.com.ar",
      "password":"b1b3773a05c0ed0176787a4f1574ff0075f7521e"
   }

]
```
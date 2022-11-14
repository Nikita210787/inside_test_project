## Application: Test project for inside (Backend)

## Stack
- JDK 17
- Maven
- Spring Boot 2.7.3
- Spring MVC
- Spring Data JPA (Hibernate)
- Spring Security
- Lombok
- JUnit 5(НЕ УСПЕЛ)
- H2

- Credentials:

```
Admin: admin / admin
User:  user / user
```

## For assembly
1)Jast add H2 data base
2)Create inmemory H2 DB with URL jdbc:h2:mem:in-mem
3)Create Remote H2 DB with URL jdbc:h2:tcp://localhost:9092/mem:in-mem. HOST localHost.
4)Assign data base
5)RUN MyApp

## For test

POST localhost:8080/authenticate
for cmd:     curl -X POST http://localhost:8080/authenticate --header Content-Type:application/json -d "{\"name\":\"admin\",\"password\":\"password\"}"
### Запрос в HHTP Client в IDEA
POST http://localhost:8080/authenticate
Content-Type: application/json

{
"name":"admin",
"password":"password"
}


POST http://localhost:8080/message ("any mesage")
for cmd:      curl -X POST -H "Authorization:Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODU0MDEwNiwiaWF0IjoxNjY4NDIwMTA2LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.z_DKleQi7zpAmlbjVTXz6LyydJSsJ494-ICXTNEfmZI" -H Content-Type: application/json --data-raw "{\"name\":\"admin\",\"message\":\"any message\"}"  http://localhost:8080/message
### Запрос в HHTP Client в IDEA
POST http://localhost:8080/message
Content-Type: application/json
Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODQ3NTk1NSwiaWF0IjoxNjY4MzU1OTU1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.y961-EIGLqRI0UfnYpbjmcDleq0pD7hSHSDZ47ppxZ8

{
"name":"admin",
"message":"any message"
}



POST http://localhost:8080/message ("history 10")
for cmd:      curl -X POST -H "Authorization:Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODU0MDEwNiwiaWF0IjoxNjY4NDIwMTA2LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.z_DKleQi7zpAmlbjVTXz6LyydJSsJ494-ICXTNEfmZI" -H Content-Type: application/json --data-raw "{\"name\":\"admin\",\"message\":\"history 10\"}"  http://localhost:8080/message
### Запрос в HHTP Client в IDEA
POST http://localhost:8080/message
Content-Type: application/json
Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODQ3NTk1NSwiaWF0IjoxNjY4MzU1OTU1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.y961-EIGLqRI0UfnYpbjmcDleq0pD7hSHSDZ47ppxZ8

{
"name":"admin",
"message":"history 10"
}


GET http://localhost:8080/admin
for cmd: curl -X GET http://localhost:8080/admin -H Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODQ0OTk0NSwiaWF0IjoxNjY4MzI5OTQ1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.mEDQfW0KzAVunDyqVM_w4haUBYogJWwl1S5elflUdTk
### Запрос в HHTP Client в IDEA
GET http://localhost:8080/admin
Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODQ3NTk1NSwiaWF0IjoxNjY4MzU1OTU1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.y961-EIGLqRI0UfnYpbjmcDleq0pD7hSHSDZ47ppxZ8




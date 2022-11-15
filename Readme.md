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
POST localhost:8080/authenticate<br>
curl -X POST http://localhost:8080/authenticate --header Content-Type:application/json -d "{\"name\":\"admin\",\"password\":\"password\"}"
<br><br>POST localhost:8080/message(anymessage)<br>
curl -X POST http://localhost:8080/message -H "Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODYzNTc1MCwiaWF0IjoxNjY4NTE1NzUwLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.cyZMrTCPBwfss2pgfD6N8DJaKK3xCTMSo8kk1NF6Kio" -H Content-Type:application/json --data-raw "{\"name\":\"admin\",\"message\":\"any message\"}"
<br><br>POST localhost:8080/message("history 10")<br>
curl -X POST http://localhost:8080/message -H "Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODYzNTc1MCwiaWF0IjoxNjY4NTE1NzUwLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.cyZMrTCPBwfss2pgfD6N8DJaKK3xCTMSo8kk1NF6Kio" -H Content-Type:application/json --data-raw "{\"name\":\"admin\",\"message\":\"history 10\"}"
<br><br>
GET http://localhost:8080/admin <br>
curl -X GET http://localhost:8080/admin -H "Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODYzODIwNSwiaWF0IjoxNjY4NTE4MjA1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.ahE4LC3DkdaQbMesuozo_nff4liGwEF-sv94zgp4tvY"

curl -X GET http://localhost:8080/
//curl --location --request GET 'http://localhost:8080/'


###
GET http://localhost:8080/admin
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODQ3NTk1NSwiaWF0IjoxNjY4MzU1OTU1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.y961-EIGLqRI0UfnYpbjmcDleq0pD7hSHSDZ47ppxZ8


###
POST http://localhost:8080/message
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2ODQ3NTk1NSwiaWF0IjoxNjY4MzU1OTU1LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.y961-EIGLqRI0UfnYpbjmcDleq0pD7hSHSDZ47ppxZ8

{
"name":"admin",
"message":"myMessage"
}
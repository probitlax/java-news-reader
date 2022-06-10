

#NewsReader API

###Developer: Amirhossein Farmad

####Date: 28 Jan 2022


---
##Requirements:


| No. | Explanation                                                                                                                                                      
| --- | -----------------------------------------------------------------------------------------------------------------------------------------------------------                                                                                                                              
| 1   | Spring Boot 2, Java: Read input data from an external http source every x seconds: https://docs.coincap.io or https://newsapi.org/ or https://api.nasa.gov/                                                                                                                   |
| 2   | Process data with any rules (modify input value, add additional values)                                                                                                                   |
| 3   | Use H2 or any other in-memory RDBMS]  (#* Don’t use hibernate (JPA). Use plain SQL. As output please create endpoints)                                                                                                                   |
| 4   | By the request in browser show the last 10 or n entries (no UI, just json)                                                                                                                   |
| 5   | Delete entries by name                                                                                                                    |
| 6   | Unit tests                                                                                                                   |
| 7   | Create a Docker image for the project, so we can run this as a Docker container                                                                                                                   |


---
##Endpoints

*      curl -X GET    http://localhost:8081/articles/

*      curl -X DELETE http://localhost:8081/articles/{ArticleAuthorName}


---
##Docker

*      docker build --tag=demo-project:latest .

*      docker run -p9091:8081 demo-project:latest

*      http://localhost:9091/h2/

*      curl http://localhost:9091/articles/

---
##Swagger

http://localhost:8081/swagger-ui/


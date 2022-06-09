

#NewsReader API
###Developer: Amirhossein Farmad
####Date: 28 Jan 2022

---
##Requirements:

a.     Should be runnable from the IDE.

b.     Read input data from an external http source every x seconds (one of options below):

* https://docs.coincap.io or

* https://newsapi.org/ or

* https://api.nasa.gov/

c.     Process data with any rules (modify input value, add additional values)

d.     Use H2 or any other in-memory RDBMS

e.     As output please create endpoints

a.     By the request in browser show the last 10 or n entries (no UI, just json)

b.     Delete entries by name

f.     Don’t use hibernate (JPA). Use plain SQL.

g.     Spring Boot 2, Java

h.     No Spring integrations framework

i.     Unit tests

j.     Create a Docker image for the project, so we can run this as a Docker container

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


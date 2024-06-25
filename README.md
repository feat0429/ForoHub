# ForoHub
API Rest created as part of the ONE - Oracle Next Education bootcamp provided by Alura Latam. The project was completed following the requirements provided by 
Alura to build API Rest application using Spring Boot that retrieves forum topics and responses from a MySQL database. The architecture used for this project 
was Ports and Adapters (Hexagonal) architecture with the purpose of learning and practicing. Even if the project is simple and it does not require such a complex
architecture I wanted to add an additional challenge to learn this type of achitecture.


## Features
- Get all stored topics and the responses related to a specific topic.
- Save new topics and responses for each topic.
- Edit available topic (title, content, and status) and responses (message).
- Delete existing topics.
- User authentication with JWT.
- Registration of new users.

## Technologies and packages used

- Java JDK 21.0.2
- Spring Boot 3.2.5
- PostgreSQL JDBC Driver
- Spring Boot Starter Data JPA
- MySQL 8.0.35
- Spring Boot Starter Security
- Spring Boot Starter Validation
- Spring Boot Starter Web
- Flyway Core
- Flyway Mysql
- Mysql Connector J
- Lombok
- Java JWT 4.4.0
- Hibernate Validator 8.0.1
- Springdoc OpenAPI Starter WebMVC UI

## Running the application
1. Clone or download the project repository.
2. Make sure that you MySQL server is running.
3. Open the project with your IDE of preference.
4. Install Maven depedencies specified in the ***pom.xml*** file.
5. In the **src/main/resources/application.properties** file insert the database connection string, username and password, either by harcoding it or using enivironment variables.
6. In the same properties file you can use the default secret or a different one, either by using a environment variable or by hardcoding it.
7. Compile and run the project.

# Article Management API

A RESTful API for managing articles, built with Spring Boot 3, Spring Security with JWT authentication, Hibernate, and MySQL.

## Description

This project demonstrates how to build a basic article management system using Spring Boot. It includes features like : JWT authentication ,articles, comments,favorite aritcle and following other users.

## How to run :

### Prerequisites

To run this project locally, you'll need the following tools:

- [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven (v.3.9.8)](https://maven.apache.org/download.cgi)

### Installation Steps

1. **Clone the repository**
2. **Navigate to the project directory**
3. **Set up environment variables**
4. **Build the project**:
    ```bash
    mvn clean install
    ```
5. **Run the Spring Boot application**:

### Running in an IDE

#### IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Ensure that environment variables are configured in the IDE's Run Configurations.
3. Run the application by right-clicking on the `ChallengeApplication.java` class and selecting **Run 'Application'**.

#### Visual Studio Code

1. Open the project in VS Code.
2. Install the necessary extensions for Java and Spring Boot (Java Extension Pack, Spring Boot Extension Pack).
3. Use the terminal within VS Code to run the application:
    ```bash
    mvn spring-boot:run
    ```

## Testing

You can use Postman to test the API using the following link:

- [Postman Collection for Article Management API](https://noname-4409.postman.co/workspace/ada56cd8-6013-4e70-b405-3ff63bf8959f/collection/31896070-959af2c9-d0a3-403d-9784-d44245424286)

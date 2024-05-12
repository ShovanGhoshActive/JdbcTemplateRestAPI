# About This Spring Boot Project

This is a Spring Boot project that demonstrates basic CRUD operations using MySQL database. The project includes components for database operations, services for business logic, and a RESTful API for interacting with the database.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [Usage](#usage)
- [Endpoints](#endpoints)

## Technologies Used

- Spring Boot
- MySQL
- Java
- Maven
- Spring Data JDBC
- Spring Web

## Project Structure

The project is structured as follows:

my-spring-boot-project/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── com.shovans.demo.controller/
│ │ │ │ └── MyController.java
│ │ │ ├── com.shovans.demo.dao/
│ │ │ │ └── MyDao.java
│ │ │ ├── com.shovans.demo.service/
│ │ │ │ └── MyService.java
│ │ │ └── com.shovans.demo.common.models/
│ │ │ └── MyModel.java
│ │ └── resources/
│ │ ├── application.properties
│ │ └── data.sql
│ └── test/
│ └── java/
│ └── com.shovans.demo/
│ └── (test classes)
├── target/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md


## Setup

1. Clone the repository: `git clone https://github.com/ShovanGhoshActive/JdbcTemplateRestAPI.git`
2. Navigate to the project directory: `cd JdbcTemplateRestAPI`
3. Open `src/main/resources/application.properties` and configure the database connection settings.
4. Run the application: `./mvnw spring-boot:run`

## Usage

Once the application is running, you can access the API endpoints. See [Endpoints](#endpoints) for details.

## Endpoints

- **GET /api/list-names**: Retrieves a list of names from the 'test' table.

- **POST /api/new-names**: Saves or updates a record in the 'test' table.

  Request Body:
  ```json
  {
    "name": "John Doe",
    "id": 1
  }

- **DELETE /api/delete-names: Deletes records from the 'test' table based on the provided name.

   Request Parameter:
   name=John Doe

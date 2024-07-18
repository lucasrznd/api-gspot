<h2 align="center">SPOT Management System API</h2>

<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
</div>

<p align="center">
 <a href="#description">Description</a> • 
 <a href="#features">Features</a> • 
 <a href="#installation">Installation</a> •
 <a href="#contribution">Contribution</a> 
</p>

<h2 id="description">📙 Description</h2>

The **SPOT Management System API** is a backend service developed in Java with Spring Boot to support the SPOT Management System web application. This API provides endpoints for managing SPOTS, which are short recordings sold by a radio company. The API facilitates the registration and management of announcers, associated companies, and spots, making it easy to control and search for all registered spots.

<h2 id="features">✨ Features</h2>

- **Announcer Registration**: Endpoints to add and manage announcers who make the recordings.
- **Company Registration**: Endpoints to add and manage companies associated with the recordings.
- **Spot Registration**: Endpoints to add and manage spots, specifying the title, announcer, and associated company.

### Technologies Used

- **Java**: Primary programming language.
- **Spring Boot**: Framework for building the backend.
- **Spring Data JPA**: For data persistence and database interaction.
- **Hibernate**: ORM for mapping Java objects to database tables.
- **PostgreSQL**: Database used for storing data.
- **Maven**: For project management and dependency management.

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven for dependency management
- PostgreSQL database

<h2 id="installation">🛠️ Installation</h2>

1. Clone the repository to your local environment:

```
git clone https://github.com/lucasrznd/api-gspot.git
```

2. Configure your database:

```
spring.datasource.url=${POSTGRES_URL}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
```

3. Navigate to the project directory:

```
cd api-gspot
```

4. Compile the project using Maven:

```
mvn clean install
```

5. Run the generated JAR file in the target folder or deploy the JAR to your application server.

<h2 id="contribution">🤝 Contribution</h2>

Contributions are welcome! If you have suggestions, improvements, or find bugs, feel free to open an issue or submit a pull request.

<h2 id="author">👨🏻‍💻 Author</h2>

<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/lucasrznd"><img src="https://avatars.githubusercontent.com/u/101664450?v=4&v=" width="115px;" alt="Lucas Rezende"/><br /><sub><b>Lucas Rezende</b></sub></a><br/><a title="Código">💻</a></td>
  </tbody>
</table>

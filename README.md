<h2 align="center">SPOT Management System API</h2>

<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
</div>

<p align="center">
 <a href="#description">Description</a> • 
 <a href="#features">Features</a> • 
  <a href="#routes">API Endpoints</a> • 
 <a href="#installation">Installation</a> •
 <a href="#contribution">Contribution</a> 
</p>

<h2 id="description">📙 Description</h2>

The **SPOT Management System API** is a backend service developed in Java with Spring Boot to support the SPOT Management System web application. This API provides endpoints for managing SPOTS, which are short recordings sold by a radio company. The API facilitates the registration and management of announcers, associated companies, and spots, making it easy to control and search for all registered spots.

<h2 id="features">✨ Features</h2>

- **Announcer Registration**: Endpoints to add and manage announcers who make the recordings.
- **Company Registration**: Endpoints to add and manage companies associated with the recordings.
- **Spot Registration**: Endpoints to add and manage spots, specifying the title, announcer, and associated company.

<h2 id="routes">📍 API Endpoints</h2>

### Spot
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /spot</kbd>     | retrieves all [spots](#get-spots)
| <kbd>GET /count</kbd>     | count total existing [spots](#count-spots)
| <kbd>GET /amount-raised</kbd>     | retrieves [amount raised](#amount-raised) from spots
| <kbd>GET /calculate-price</kbd>     | calculates spot price
| <kbd>POST /spot</kbd>     | create a new [spot](#post-spot)
| <kbd>PUT /spot/id</kbd>     | update existing spot
| <kbd>DELETE /spot/id</kbd>     | delete spot by id

<h3 id="get-spots">GET /spot</h3>

**RESPONSE**
```json
{
  "id": 1,
  "title": "SUMMER PROMOTIONS",
  "company": {
    "id": 2,
    "name": "Cloth Store",
    "phoneNumber": "40988888888",
    "urlImage": ""
  },
 "announcer": {
    "id": 1,
    "name": "Lucas Rezende",
    "phoneNumber": "40999999999",
    "urlImage": ""
  },
 "date": "2024-06-11",
 "duration": 0.41,
 "activeContract": true,
 "price": 20.00
}
```

<h3 id="count-spots">GET /count</h3>

**RESPONSE**
```json
1
```

<h3 id="amount-raised">GET /amount-raised</h3>

**RESPONSE**
```json
20.00
```

<h3 id="post-spot">POST /spot</h3>

**REQUEST**
```json
{
  "id": 1,
  "title": "SUMMER PROMOTIONS",
  "company": {
    "id": 2,
    "name": "Cloth Store",
    "phoneNumber": "40988888888",
    "urlImage": ""
  },
 "announcer": {
    "id": 1,
    "name": "Lucas Rezende",
    "phoneNumber": "40999999999",
    "urlImage": ""
  },
 "duration": 0.41,
 "activeContract": true,
}
```

**RESPONSE**
```json
{
  "id": 1,
  "title": "SUMMER PROMOTIONS",
  "company": {
    "id": 2,
    "name": "Cloth Store",
    "phoneNumber": "40988888888",
    "urlImage": ""
  },
 "announcer": {
    "id": 1,
    "name": "Lucas Rezende",
    "phoneNumber": "40999999999",
    "urlImage": ""
  },
 "date": "2024-06-11",
 "duration": 0.41,
 "activeContract": true,
 "price": 20.00
}
```

### Company
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /company</kbd>     | retrieves all [companies](#get-companies)
| <kbd>GET /count</kbd>     | count total existing companies
| <kbd>POST /company</kbd>     | create a new [company](#post-company)
| <kbd>PUT /company/id</kbd>     | update existing company
| <kbd>DELETE /company/id</kbd>     | delete company by id

<h3 id="get-companies">GET /company</h3>

**RESPONSE**
```json
{
  "id": 1,
  "name": "Lucas Company",
  "phoneNumber": "40999999999",
  "urlImage": ""
},
{
  "id": 2,
  "name": "Cloth Store",
  "phoneNumber": "40988888888",
  "urlImage": ""
}
```

<h3 id="count-companies">GET /count</h3>

**RESPONSE**
```json
2
```

<h3 id="post-company">POST /company</h3>

**REQUEST**
```json
{
  "name": "Lucas Company",
  "phoneNumber": "40999999999",
  "urlImage": ""
}
```

**RESPONSE**
```json
{
  "id": 1,
  "name": "Lucas Company",
  "phoneNumber": "40999999999",
  "urlImage": ""
}
```

### Announcer
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /announcer</kbd>     | retrieves all [announcers](#get-announcers)
| <kbd>GET /count</kbd>     | count total existing [announcers](#count-announcers)
| <kbd>POST /announcer</kbd>     | create a new [announcer](#post-announcer)
| <kbd>PUT /announcer/id</kbd>     | update existing announcer
| <kbd>DELETE /announcer/id</kbd>     | delete announcer by id

<h3 id="get-announcers">GET /announcer</h3>

**RESPONSE**
```json
{
  "id": 1,
  "name": "Lucas Rezende",
  "phoneNumber": "40999999999",
  "urlImage": ""
}
```

<h3 id="count-announcers">GET /count</h3>

**RESPONSE**
```json
1
```

<h3 id="post-announcer">POST /announcer</h3>

**REQUEST**
```json
{
  "name": "Lucas Rezende",
  "phoneNumber": "40999999999",
  "urlImage": ""
}
```

**RESPONSE**
```json
{
  "id": 1,
  "name": "Lucas Rezende",
  "phoneNumber": "40999999999",
  "urlImage": ""
}
```

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

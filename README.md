<h2 align="center">API de gerenciamento de SPOTS</h2>

<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
</div>

<p align="center">
 <a href="#description">Descrição</a> • 
 <a href="#features">Funcionalidades</a> • 
 <a href="#installation">Instalação</a> •
 <a href="#contribution">Contribuição</a> 
</p>

<h2 id="description">📙 Descrição</h2>

A **API de gerenciamento de SPOTS** é um serviço backend desenvolvido em Java com Spring Boot. Esta API fornece endpoints para gerenciar SPOTS, que são gravações curtas vendidas por uma empresa de rádio. A API facilita o registro e gerenciamento de locutores, empresas associadas e spots, tornando fácil controlar e consultar todos os spots registrados.

<h2 id="features">✨ Funcionalidades</h2>

- **Cadastro de Locutores**: Endpoints para adicionar e gerenciar locutores que realizam as gravações.
- **Cadastro de Empresas**: Endpoints para adicionar e gerenciar empresas associadas às gravações.
- **Cadastro de Spots**: Endpoints para adicionar e gerenciar spots, especificando o título, locutor e empresa associada.

### Tecnologias Utilizadas

- **Java**: Linguagem de programação.
- **Spring Boot**: Framework para construção do backend.
- **Spring Data JPA**: Para persistência de dados e interação com o banco de dados.
- **Hibernate**: ORM para mapeamento de objetos Java em tabelas do banco de dados.
- **PostgreSQL**: Banco de dados utilizado para armazenamento dos dados.

### Pré-requisitos

- Java Development Kit (JDK) 17 ou superior
- Maven para gerenciamento de dependências
- Banco de dados PostgreSQL

<h2 id="installation">🛠️ Instalação</h2>

1. Clone o repositório para o seu ambiente local:

```
git clone https://github.com/lucasrznd/api-gspot.git
```

2. Configure o banco de dados no arquivo **application.properties**:

```
spring.datasource.url=${POSTGRES_URL}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
```

4. Compile o projeto utilizando o Maven:

```
mvn clean install
```

<h2 id="contribution">🤝 Contribuição</h2>

Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou encontrar bugs, sinta-se à vontade para abrir uma issue ou enviar um pull request.

<h2 id="author">👨🏻‍💻 Autor</h2>

<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/lucasrznd"><img src="https://avatars.githubusercontent.com/u/101664450?v=4&v=" width="115px;" alt="Lucas Rezende"/><br /><sub><b>Lucas Rezende</b></sub></a><br/><a title="Código">💻</a></td>
  </tbody>
</table>

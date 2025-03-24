# Product API

This project is a Spring Boot application that provides REST and GraphQL APIs for filtering and sorting products. It uses PostgreSQL for data persistence and can be run using Docker.

## Technologies

- Java 21
- Spring Boot 3.x
- PostgreSQL
- Docker
- GraphQL
- Swagger (OpenAPI)

## Prerequisites

- Java 21
- Maven
- Docker and Docker Compose

## Getting Started

### Clone the repository

```bash
git clone https://github.com/yourusername/product-api.git
cd product-api
````

### Build the project

```bash
mvn clean package
```
Run with Docker
```bash
docker-compose up --build
```

The application will be available at http://localhost:8080

API Documentation
Swagger UI is available at http://localhost:8080/swagger-ui.html

OpenAPI specification is available at http://localhost:8080/api-docs

### API Endpoints
REST API
Filter products by price range:

GET /api/filter/price/{initialRange}/{finalRange}
Sort products by price:

GET /api/sort/price
GraphQL API
GraphQL endpoint: /graphql

GraphiQL interface: http://localhost:8080/graphiql (available in non-production environments)

Example queries:

```graphql
query {
  filterProductsByPrice(initialRange: 1000, finalRange: 5000) {
    barcode
    item
    price
  }
}
```

```graphql
query {
  sortProductsByPrice
}
```
---
### Development
Using H2 Database for Development
The project is configured to use H2 in-memory database for development. To access the H2 console:

Go to http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: password
--- 
### Running tests
```bash
mvn test
```


### Configuration
Application configuration can be found in src/main/resources/application.yml.

### Docker
The Dockerfile and docker-compose.yml are provided to containerize the application and its PostgreSQL database.

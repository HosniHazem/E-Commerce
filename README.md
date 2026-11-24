# E-Commerce Service

A Spring Boot REST API for an online shop: catalogue, orders, checkout and
reviews, secured with JSON Web Tokens.

## What it covers

- Products and categories
- Shops
- Orders and checkout
- Coupons
- Reviews
- Registration and login, with JWT issued on authentication
- Role-based authorisation through Spring Security

## Structure

```
src/main/java/com/succes/ecommerce/
  JWTConfiguration/   security config, token provider, auth filter, user details
  Repository/         Spring Data JPA repositories
  controller/         REST endpoints
  config/             Swagger
```

## Stack

Java · Spring Boot · Spring Security · Spring Data JPA · JWT · Swagger · Maven

## Running it

```bash
./mvnw spring-boot:run
```

Datasource settings live in `src/main/resources/application.properties`.
Swagger UI is served at `/swagger-ui.html` once the app is up.

## Note

This service is also vendored into the `Integration-Project` repository, where
it runs behind an API gateway alongside a complaints service.

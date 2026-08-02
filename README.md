# Logistic Platform

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Gradle](https://img.shields.io/badge/Gradle-9.6-02303A)
![License](https://img.shields.io/badge/License-BSD_2--Clause-blue.svg)

Backend logistics platform built with **Java 21**, **Spring Boot** and **PostgreSQL**.

The goal of this project is to create a scalable warehouse and order management system while following modern backend
development practices.

---

# Features

- ✅ REST API for product management
- ✅ PostgreSQL integration
- ✅ Flyway database migrations
- ✅ Spring Data JPA
- ✅ JPA Auditing (`createdAt`)
- ✅ Embedded value objects (`Dimensions`)
- ✅ Environment-based configuration
- ✅ Gradle Kotlin DSL

---

# Tech Stack

| Technology      | Version |
|-----------------|---------|
| Java            | 21      |
| Spring Boot     | 4.1     |
| Spring Data JPA | Latest  |
| Hibernate       | 7       |
| PostgreSQL      | 17      |
| Flyway          | Latest  |
| Gradle          | 9.6     |
| Lombok          | Latest  |

---

# Project Structure

```text
src
├── main
│   ├── java
│   │   └── org.example.logisticplatform
│   │       ├── config
│   │       └── product
│   │       └── ProductController
│   │       └── productService
│   └── resources
│       ├── application.yaml
│       └── db
│           └── migration
│               └── V1__create_products.sql
└── test
```

---

# API

| Method | Endpoint             | Description          |
|--------|----------------------|----------------------|
| GET    | `/api/products`      | Get all products     |
| GET    | `/api/products/{id}` | Get product by ID    |
| POST   | `/api/products`      | Create a new product |
| PUT    | `/api/products/{id}` | Update a product     |
| DELETE | `/api/products/{id}` | Delete a product     |

---

# Roadmap

- [x] Configure PostgreSQL
- [x] Configure Flyway
- [x] Create Product entity
- [x] Add Product repository
- [x] Implement Product REST API
- [x] Enable JPA Auditing
- [x] Update endpoint (`PUT`)

### Next

- [ ] Add ProductService
- [ ] Introduce DTOs
- [ ] Request validation
- [ ] Update endpoint (`PATCH`)
- [ ] Global exception handling
- [ ] Category entity
- [ ] Supplier entity
- [ ] Warehouse module
- [ ] Inventory management
- [ ] Order management
- [ ] Authentication & Authorization
- [ ] Docker support
- [ ] Unit & Integration tests

---

# Getting Started

## Requirements

- Java 21
- PostgreSQL 17+
- Gradle (or use the Gradle Wrapper)

## Clone repository

```bash
git clone https://gitlab.com/<username>/logistic-platform.git
cd logistic-platform
```

## Configure environment variables

| Variable      | Description         |
|---------------|---------------------|
| `DB_URL`      | PostgreSQL JDBC URL |
| `DB_USERNAME` | Database username   |
| `DB_PASSWORD` | Database password   |

Example:

```text
DB_URL=jdbc:postgresql://localhost:5432/logisticsystem
DB_USERNAME=admin
DB_PASSWORD=admin
```

## Run the application

Using Gradle Wrapper:

```bash
./gradlew bootRun
```

Windows:

```powershell
.\gradlew.bat bootRun
```

The application will be available at:

```text
http://localhost:8080
```

---

# Database

Database schema is managed using **Flyway**.

Migrations are located in:

```text
src/main/resources/db/migration
```

---

# Troubleshooting

Common problems and solutions.

| Problem                                                                 | Cause                                                                 | Solution                                                                                                   |
|-------------------------------------------------------------------------|-----------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| Spring Boot application fails to start due to PostgreSQL timezone error | System timezone uses an unsupported or deprecated timezone identifier | Set the timezone to a valid IANA timezone identifier, for example `UTC`, `Europe/London`, or `Europe/Kyiv` |

Example error:

```text
SQL State  : 22023
Error Code : 0
Message    : FATAL: invalid value for parameter "TimeZone": "Europe/Kiev"
```

---

# HTTP Requests

Example requests are stored in the `http/` directory and can be executed directly from IntelliJ IDEA Ultimate.

---

# Changelog

Project history is available in [CHANGELOG.md](CHANGELOG.md).

---

# License

This project is licensed under the **BSD 2-Clause License**.

See the [LICENSE](LICENSE) file for more information.
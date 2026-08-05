# Logistic Platform

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Gradle](https://img.shields.io/badge/Gradle-9.6-02303A?logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-28.5-2496ED?logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/Docker_Compose-v2-2496ED?logo=docker&logoColor=white)
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
- ✅ Docker support
- ✅ Docker Compose

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
| Docker          | Latest  |
| Docker Compose  | Latest  |
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
- [x] Docker support
- [x] Docker Compose

### Next

- [ ] Kubernetes
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
- [ ] Unit & Integration tests

---

# Getting Started

## Requirements

- Java 21
- PostgreSQL 17+
- Gradle (or use the Gradle Wrapper)
- Docker Desktop
- Docker Compose

## Clone repository

```bash
git clone https://gitlab.com/<username>/logistic-platform.git
cd logistic-platform
```

## Start the application

Build and start all services:

```bash
docker compose up --build
```

Run in background:

```bash
docker compose up -d
```

Stop all services:

```bash
docker compose down
```

PostgreSQL:

```text
Host: localhost
Port: 5432
Database: logistic
Username: postgres
Password: postgres
```

The application will be available at:

```text
http://localhost:8080
```

---

# Database

The application uses **PostgreSQL 17** running inside a Docker container.

Database schema is managed automatically by **Flyway** during application startup.

Migration scripts are located in:

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

# Docker Architecture

The application consists of two containers:

```text
┌────────────────────┐
│ logistic-app       │
│ Spring Boot        │
└─────────┬──────────┘
          │
          │
┌─────────▼──────────┐
│ logistic-postgres  │
│ PostgreSQL 17      │
└────────────────────┘
```

Both containers are orchestrated using **Docker Compose**.

# Changelog

Project history is available in [CHANGELOG.md](CHANGELOG.md).

---

# License

This project is licensed under the **BSD 2-Clause License**.

See the [LICENSE](LICENSE) file for more information.
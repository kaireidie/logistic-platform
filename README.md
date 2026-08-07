# Logistic Platform

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-brightgreen)
![Kubernetes](https://img.shields.io/badge/Kubernetes-1.30-326CE5?logo=kubernetes&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Gradle](https://img.shields.io/badge/Gradle-9.6-02303A?logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-28.5-2496ED?logo=docker&logoColor=white)
![License](https://img.shields.io/badge/License-BSD_2--Clause-blue.svg)

Backend logistics platform built with **Java 21**, **Spring Boot**, **Kubernetes** and **PostgreSQL**.

The goal of this project is to create a scalable warehouse and order management system while following modern backend
development and DevOps practices.

---

# Features

- ✅ REST API for product management
- ✅ Kubernetes deployment with NGINX Ingress Controller
- ✅ Automated one-click deployment script (`deploy.sh`)
- ✅ Health monitoring via Spring Boot Actuator
- ✅ PostgreSQL integration
- ✅ Flyway database migrations
- ✅ Spring Data JPA & JPA Auditing (`createdAt`)
- ✅ Embedded value objects (`Dimensions`)
- ✅ Environment-based configuration
- ✅ Gradle Kotlin DSL
- ✅ Containerization via Docker & Docker Compose

---

# Tech Stack

| Technology               | Version |
|--------------------------|---------|
| Java                     | 21      |
| Spring Boot              | 3.x     |
| Spring Data JPA          | Latest  |
| Hibernate                | 6 / 7   |
| PostgreSQL               | 17      |
| Flyway                   | Latest  |
| Kubernetes (Kind)        | 1.30+   |
| NGINX Ingress Controller | Latest  |
| Docker & Docker Compose  | Latest  |
| Gradle (Kotlin DSL)      | 9.6     |
| Lombok                   | Latest  |

---

# Project Structure

```text
├── k8s/                        # Kubernetes manifests (Deployments, Services, Ingress)
│   └── app/
├── src/
│   ├── main/
│   │   ├── java/org/example/logisticplatform/
│   │   │   ├── config/
│   │   │   └── product/
│   │   └── resources/
│   │       ├── application.yaml
│   │       └── db/migration/   # Flyway SQL migrations
│   └── test/
├── deploy.sh                   # One-click Bash deploy script (Linux / macOS / Git Bash)
├── docker-compose.yaml
└── build.gradle.kts
```

---

# API

| Method | Endpoint             | Description          |
|--------|----------------------|----------------------|
| GET    | `/api/products`      | Get all products     |
| GET    | `/api/products/{id}` | Get product by ID    |
| POST   | `/api/products`      | Create a new product |
| PUT    | `/api/products/{id}` | Update a product     |
| PATCH  | `/api/products/{id}` | Partially update a product |
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
- [x] Kubernetes cluster setup & Ingress routing
- [x] Spring Boot Actuator monitoring
- [x] One-click deployment scripts (deploy.sh)

### Next

- [ ] Add ProductService
- [ ] Introduce DTOs
- [ ] Request validation
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

## 🚀 One-Click Deployment (Kubernetes - Recommended)

1. **Clone the repository:**

   ```bash
   git clone https://github.com/kaireidie/logistic-platform
   cd logistic-platform
    ```
2. **Run the automated deployment script:**

   Linux / macOS / Git Bash:
   ```bash
   chmod +x deploy.sh
   ./deploy.sh
   ```
   The script automatically creates the required namespace, installs the NGINX Ingress Controller, waits for it to
   become ready, and applies all Kubernetes manifests.


3. **Verify access:**
    * **API / Application:** `http://localhost`
    * **Health Check:** `http://localhost/actuator/health`

## 🐳 Docker Compose Alternative

If you prefer to run services without Kubernetes:

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
| \r: command not found when running deploy.sh                            | Windows CRLF line endings in Bash script                              | Change line endings of deploy.sh to LF in your IDE(etc.) or use .gitattributes                             |

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

# Kubernetes Architecture

The application consists of two containers:

```text
[ Client Request ]
                                  │
                                  ▼
                   ┌──────────────────────────────┐
                   │   NGINX Ingress Controller   │
                   │    (http://localhost:80)     │
                   └──────────────┬───────────────┘
                                  │
                                  ▼
                   ┌──────────────────────────────┐
                   │    logistic-app Service      │
                   └──────────────┬───────────────┘
                                  │
                                  ▼
                   ┌──────────────────────────────┐
                   │   logistic-platform Pod      │
                   │     (Spring Boot App)        │
                   └──────────────┬───────────────┘
                                  │
                                  ▼
                   ┌──────────────────────────────┐
                   │    logistic-postgres Pod     │
                   │       (PostgreSQL 17)        │
                   └──────────────────────────────┘
```

# Changelog

Project history is available in [CHANGELOG.md](CHANGELOG.md).

---

# License

This project is licensed under the **BSD 2-Clause License**.

See the [LICENSE](LICENSE) file for more information.
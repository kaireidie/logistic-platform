# Changelog

## [Unreleased]

### Added

* Added **Kubernetes support** with manifests for Deployment, Service, and Ingress routing.
* Integrated **NGINX Ingress Controller** to route local traffic via standard port `80` (`http://localhost`).
* Added Spring Boot Starter Actuator for application monitoring (`/actuator/health`).
* Added automated deployment scripts:
    * `deploy.sh` (Bash for Linux / macOS / Git Bash).
* Added `.gitattributes` to enforce `LF` line endings for shell scripts across operating systems.
* Implemented the initial Product API.
* Added Product entity and embedded Dimensions.
* Added ProductRepository.
* Added ProductController with basic CRUD endpoints.
* Configured JPA Auditing for automatic creation timestamps.
* Added HTTP requests for API testing.
* Implemented the PUT endpoint for updating products.
* Implemented the PATCH endpoint for partial product updates.
* Added Docker support for containerized application deployment.
* Added Docker Compose configuration for running the application and PostgreSQL with a single command.

### Changed

* Updated Kubernetes Ingress host configuration to simplify local access without modifying system hosts files.
* Renamed the project from `logisctic-platform` to `logistic-platform`.
* Fixed project/package naming typo (`logisctic` → `logistic`).
* Refactored the PUT endpoint to update the existing entity and return `204 No Content`.
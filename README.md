# Logistic Platform

## Progress

### Completed

* ✅ Configured PostgreSQL connection using environment variables.
* ✅ Added Flyway and created the initial database migration.
* ✅ Created the `Product` entity with embedded `Dimensions`.
* ✅ Enabled JPA Auditing (`createdAt`).
* ✅ Implemented `ProductRepository`.
* ✅ Implemented a basic REST controller for products (GET, POST, DELETE).
* ✅ Successfully tested product creation via HTTP request.

## TODO

* [ ] Add `ProductService`.
* [ ] Introduce DTOs (`ProductRequest` / `ProductResponse`).
* [ ] Add request validation.
* [ ] Implement update (`PUT`) endpoint.
* [ ] Add global exception handling.
* [ ] Write project requirements.
* [ ] Add setup and run instructions.

## Environment Variables

| Variable      | Description         |
| ------------- | ------------------- |
| `DB_URL`      | PostgreSQL JDBC URL |
| `DB_USERNAME` | Database username   |
| `DB_PASSWORD` | Database password   |

### Example

```text
DB_URL=jdbc:postgresql://localhost:5432/logisticsystem
DB_USERNAME=admin
DB_PASSWORD=admin
```
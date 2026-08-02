# Changelog

## [Unreleased]

### Added

- Implemented the initial Product API.
- Added Product entity and embedded Dimensions.
- Added ProductRepository.
- Added ProductController with basic CRUD endpoints.
- Configured JPA Auditing for automatic creation timestamps.
- Added HTTP requests for API testing.
- Implemented the PUT endpoint for updating products.
- Started implementing the PATCH endpoint for partial product updates.

### Changed

- Renamed the project from `logisctic-platform` to `logistic-platform`.
- Fixed project/package naming typo (`logisctic` → `logistic`).
- Refactored the PUT endpoint to update the existing entity and return `204 No Content`.
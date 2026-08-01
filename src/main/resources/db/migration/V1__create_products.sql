CREATE TABLE products
(
    id          BIGSERIAL PRIMARY KEY,
    NAME        VARCHAR(255) NOT NULL,
    description TEXT,
    photo_url   VARCHAR(500),
    price       NUMERIC(10, 2) NOT NULL,
    length      NUMERIC(10, 2) NOT NULL,
    width       NUMERIC(10, 2) NOT NULL,
    height      NUMERIC(10, 2) NOT NULL,
    category_id BIGINT NOT NULL,
    supplier_id BIGINT NOT NULL,
    created_at  TIMESTAMP WITH time zone NOT NULL
);
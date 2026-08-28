package org.example.logisticplatform.product.dto;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link org.example.logisticplatform.product.Product}
 */
public record ProductResponseDto
        (
                Long id,
                String name,
                String description,
                String photoUrl,
                BigDecimal price,
                DimensionsDto dimensions,
                Long categoryId,
                Long supplierId,
                Instant createdAt
        ) {
}
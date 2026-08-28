package org.example.logisticplatform.product.dto;

import java.math.BigDecimal;

/**
 * DTO for {@link org.example.logisticplatform.product.Product}
 */
public record ProductRequestDto
        (
                String name,
                String description,
                String photoUrl,
                BigDecimal price,
                DimensionsDto dimensions,
                Long categoryId,
                Long supplierId
        ) {
}
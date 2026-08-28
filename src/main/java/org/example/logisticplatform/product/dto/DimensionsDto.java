package org.example.logisticplatform.product.dto;

import java.math.BigDecimal;

/**
 * DTO for {@link org.example.logisticplatform.product.Dimensions}
 */
public record DimensionsDto
        (
                BigDecimal length,
                BigDecimal width,
                BigDecimal height
        ) {
}
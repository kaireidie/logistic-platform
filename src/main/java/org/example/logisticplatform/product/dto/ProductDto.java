package org.example.logisticplatform.product.dto;

import org.example.logisticplatform.product.Dimensions;
import org.example.logisticplatform.product.Product;

import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
public record ProductDto(String name, String description, String photoUrl, BigDecimal price, Dimensions dimensions,
                         Long categoryId, Long supplierId) {

}
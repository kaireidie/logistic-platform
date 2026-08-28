package org.example.logisticplatform.product;

import org.example.logisticplatform.product.dto.ProductRequestDto;
import org.example.logisticplatform.product.dto.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toResponseDto(Product product);

    Product toEntity(ProductRequestDto dto);

}

package org.example.logisticplatform.product;

import org.example.logisticplatform.product.dto.ProductRequestDto;
import org.example.logisticplatform.product.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Из Entity в Response DTO
    ProductResponseDto toResponseDto(Product product);

    // Из Request DTO в Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(ProductRequestDto dto);
}
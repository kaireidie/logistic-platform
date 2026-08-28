package org.example.logisticplatform.product;

import org.example.logisticplatform.product.dto.ProductRequestDto;
import org.example.logisticplatform.product.dto.ProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDto).toList();
    }

    public ProductResponseDto getById(Long id) {
        return productRepository.findById(id).map(productMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = productMapper.toEntity(dto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(product);
        return productMapper.toResponseDto(product);
    }

    @Transactional
    public ProductResponseDto put(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setCategoryId(dto.categoryId());
        product.setPhotoUrl(dto.photoUrl());
        product.setSupplierId(dto.supplierId());

        if (dto.dimensions() != null) {
            Dimensions dimensions = product.getDimensions();
            if (dimensions == null) {
                dimensions = new Dimensions();
            }
            dimensions.setLength(dto.dimensions().length());
            dimensions.setWidth(dto.dimensions().width());
            dimensions.setHeight(dto.dimensions().height());
            product.setDimensions(dimensions);
        } else {
            product.setDimensions(null);
        }

        return productMapper.toResponseDto(product);
    }

    @Transactional
    public ProductResponseDto update(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (dto.name() != null) {
            product.setName(dto.name());
        }

        if (dto.description() != null) {
            product.setDescription(dto.description());
        }

        if (dto.dimensions() != null) {
            Dimensions dimensions = product.getDimensions();
            if (dimensions == null) {
                dimensions = new Dimensions();
            }
            dimensions.setLength(dto.dimensions().length());
            dimensions.setWidth(dto.dimensions().width());
            dimensions.setHeight(dto.dimensions().height());
            product.setDimensions(dimensions);
        }

        if (dto.price() != null) {
            product.setPrice(dto.price());
        }

        if (dto.categoryId() != null) {
            product.setCategoryId(dto.categoryId());
        }

        if (dto.photoUrl() != null) {
            product.setPhotoUrl(dto.photoUrl());
        }

        if (dto.supplierId() != null) {
            product.setSupplierId(dto.supplierId());
        }
        return productMapper.toResponseDto(product);
    }

}
package org.example.logisticplatform.product;

import org.example.logisticplatform.product.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public Product create(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPhotoUrl(dto.photoUrl());
        product.setPrice(dto.price());
        product.setDimensions(dto.dimensions());
        product.setCategoryId(dto.categoryId());
        product.setSupplierId(dto.supplierId());

        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void put(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setDimensions(dto.dimensions());
        product.setPrice(dto.price());
        product.setCategoryId(dto.categoryId());
        product.setPhotoUrl(dto.photoUrl());
        product.setSupplierId(dto.supplierId());

    }

    @Transactional
    public void update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (dto.name() != null) {
            product.setName(dto.name());
        }

        if (dto.description() != null) {
            product.setDescription(dto.description());
        }

        if (dto.dimensions() != null) {
            product.setDimensions(dto.dimensions());
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
    }

}
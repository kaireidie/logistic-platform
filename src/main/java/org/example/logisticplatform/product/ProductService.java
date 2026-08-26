package org.example.logisticplatform.product;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    public ResponseEntity<Void> put(
            @PathVariable Long id,
            @RequestBody Product updateProduct
    ) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setDimensions(updateProduct.getDimensions());
        product.setPrice(updateProduct.getPrice());
        product.setCategoryId(updateProduct.getCategoryId());
        product.setPhotoUrl(updateProduct.getPhotoUrl());
        product.setSupplierId(updateProduct.getSupplierId());

        productRepository.save(product);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody Product patchProduct
    ) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (patchProduct.getName() != null) {
            product.setName(patchProduct.getName());
        }

        if (patchProduct.getDescription() != null) {
            product.setDescription(patchProduct.getDescription());
        }

        if (patchProduct.getDimensions() != null) {
            product.setDimensions(patchProduct.getDimensions());
        }

        if (patchProduct.getPrice() != null) {
            product.setPrice(patchProduct.getPrice());
        }

        if (patchProduct.getCategoryId() != null) {
            product.setCategoryId(patchProduct.getCategoryId());
        }

        if (patchProduct.getPhotoUrl() != null) {
            product.setPhotoUrl(patchProduct.getPhotoUrl());
        }

        if (patchProduct.getSupplierId() != null) {
            product.setSupplierId(patchProduct.getSupplierId());
        }

        productRepository.save(product);

        return ResponseEntity.noContent().build();
    }
}
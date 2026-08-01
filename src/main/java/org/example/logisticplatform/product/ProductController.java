package org.example.logisticplatform.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}

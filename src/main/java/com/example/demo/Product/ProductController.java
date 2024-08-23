package com.example.demo.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.queryhandlers.GetAllQueryProductsHandler;

@SuppressWarnings("rawtypes")

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GetAllQueryProductsHandler getAllQueryProductsHandler;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return getAllQueryProductsHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        productRepository.save(product);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}

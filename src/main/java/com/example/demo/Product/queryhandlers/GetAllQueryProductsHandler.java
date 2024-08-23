package com.example.demo.Product.queryhandlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Query;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.Model.Product;

@Service
public class GetAllQueryProductsHandler implements Query<Void, List<Product>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> execute(Void input) {
        return ResponseEntity.ok(productRepository.findAll());
    }

}

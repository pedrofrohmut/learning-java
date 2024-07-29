package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ProductRecordDto;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Create new products
     * Public POST /api/products
     */
    @PostMapping("")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto reqBody) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(reqBody, productModel);
        var productCreated = productRepository.save(productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    /**
     * Get all the products
     * Public GET /api/products
     */
    @GetMapping("")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        var products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    /**
     * Get a product by id
     * Public GET /api/products/{productId}
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable UUID productId) {
        var found = productRepository.findById(productId);
        if (!found.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(found.get());
    }

    /**
     * Update a product
     * Public PUT /api/products/{productId}
     */
    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID productId,
            @RequestBody @Valid ProductRecordDto reqBody) {
        var found = productRepository.findById(productId);
        if (!found.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product cannot be found to be updated");
        }
        var productModel = found.get();
        BeanUtils.copyProperties(reqBody, productModel);
        var updated = productRepository.save(productModel);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    /**
     * Remove a product
     * Public DELETE /api/products/{productId}
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID productId) {
        var found = productRepository.findById(productId);
        if (!found.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found to be removed");
        }
        productRepository.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product removed");
    }
}

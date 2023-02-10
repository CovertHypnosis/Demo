package com.example.demo.controllers;

import com.example.demo.dtos.ProductPurchaseDTO;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public Flux<Product> getAllProducts() {
        return Flux.fromIterable(productService.getAllProducts());
    }

    @GetMapping("/{category}")
    public Flux<Product> getProductsByCategory(@PathVariable("category") String category) {
        return Flux.fromIterable(productService.getProductsByCategory(category));
    }

    @PostMapping("/buy")
    public Mono<Void> buyProduct(@RequestBody ProductPurchaseDTO purchaseDTO) {
        productService.buyProduct(purchaseDTO);
        return Mono.empty();
    }
}

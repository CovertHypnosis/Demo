package com.example.demo.services;

import com.example.demo.dtos.ProductPurchaseDTO;
import com.example.demo.enums.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.pubsub.api.reactive.RedisPubSubReactiveCommands;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ObjectMapper mapper;
    private final static String CHANNEL_NAME = "purchase";
    private final RedisPubSubReactiveCommands<String, String> reactiveSubscriber;

    public ProductService(ProductRepository productRepository,
                          ObjectMapper mapper,
                          RedisPubSubReactiveCommands<String, String> reactiveSubscriber) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.reactiveSubscriber = reactiveSubscriber;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findProductByCategory(Category.valueOf(category));
    }

    public void buyProduct(ProductPurchaseDTO purchaseDTO) {
        Product product = productRepository.findById(purchaseDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Following product cannot be found"));
        if (product.getCount() < purchaseDTO.getProductCount()) {
            throw new RuntimeException("Error count cannot be more then");
        }

        reactiveSubscriber.publish(CHANNEL_NAME, serializePurchaseDTO(purchaseDTO))
                .subscribe();
    }

    private String serializePurchaseDTO(ProductPurchaseDTO purchaseDTO) {
        try {
            return mapper.writeValueAsString(purchaseDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.demo.controllers;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.dtos.SearchDTO;
import com.example.demo.services.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<OrderDTO> getAllOrders() {
        return Flux.fromIterable(orderService.getAllOrders());
    }

    @PostMapping("/search")
    public Flux<OrderDTO> search(@RequestBody SearchDTO searchDTO) {
        return Flux.fromIterable(orderService.search(searchDTO));
    }
}

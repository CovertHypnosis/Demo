package com.example.demo.services;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.dtos.SearchDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final EntityManager entityManager;
    private final OrderRepository orderRepository;

    public OrderService(EntityManager entityManager, OrderRepository orderRepository) {
        this.entityManager = entityManager;
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::orderToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> search(SearchDTO searchDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> order = cq.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchDTO.getStartDate() != null) {
            predicates.add(cb.greaterThan(order.get("createdOn"), searchDTO.getStartDate()));
        }
        if((searchDTO.getEndDate() != null)) {
            predicates.add(cb.lessThan(order.get("createdOn"), searchDTO.getEndDate()));
        }
        if (searchDTO.getCountFrom() != null) {
            predicates.add(cb.greaterThan(order.get("productCount"), searchDTO.getCountFrom()));
        }
        if((searchDTO.getCountTo() != null)) {
            predicates.add(cb.lessThan(order.get("productCount"), searchDTO.getCountTo()));
        }
        if (searchDTO.getPriceFrom() != null) {
            predicates.add(cb.greaterThan(order.get("totalPrice"), searchDTO.getPriceFrom()));
        }
        if((searchDTO.getPriceTo() != null)) {
            predicates.add(cb.lessThan(order.get("totalPrice"), searchDTO.getPriceTo()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList()
                .stream()
                .map(this::orderToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO orderToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .userDTO(convertUserToDTO(order.getUser()))
                .product(order.getProduct())
                .status(order.getStatus())
                .createdOn(order.getCreatedOn())
                .updatedOn(order.getUpdateOn())
                .productCount(order.getProductCount())
                .totalPrice(order.getTotalPrice())
                .build();
    }
    private UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}

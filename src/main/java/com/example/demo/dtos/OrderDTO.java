package com.example.demo.dtos;

import com.example.demo.enums.Status;
import com.example.demo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDTO {
    private Long id;
    private UserDTO userDTO;
    private Product product;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private Status status;
    private Double totalPrice;
    private Long productCount;
}

package de.krearthur.shop.dto;

import de.krearthur.shop.model.Product;
import de.krearthur.shop.model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private Long id;
    private User user;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}

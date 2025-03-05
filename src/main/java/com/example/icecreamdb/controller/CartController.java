package com.example.icecreamdb.controller;

import com.example.icecreamdb.entity.ProductBasket;
import com.example.icecreamdb.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/cart")
class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId, HttpServletRequest request, HttpServletResponse response) {
        cartService.addProduct(productId, request, response);
        return ResponseEntity.ok("Product added to cart");
    }

    @GetMapping("/items")
    public List<ProductBasket> getCartItems(HttpServletRequest request) {
        return cartService.getCartItems(request);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(HttpServletRequest request) {
        cartService.checkout(request);
        return ResponseEntity.ok("Order placed successfully");
    }
}

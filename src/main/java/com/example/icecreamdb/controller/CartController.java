package com.example.icecreamdb.controller;

import com.example.icecreamdb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String showCart() {
        // Логика для отображения корзины
        return "cart"; // Страница, которая отображает содержимое корзины
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId) {
        cartService.addProduct(productId);
        return "redirect:/cart"; // После добавления возвращаемся в корзину
    }




}

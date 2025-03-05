package com.example.icecreamdb.controller;

import com.example.icecreamdb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") Long productId) {
        // Логика добавления товара в корзину
        return "redirect:/cart";  // Перенаправление на страницу корзины
    }

}

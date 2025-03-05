package com.example.icecreamdb.controller;

import com.example.icecreamdb.entity.ProductBasket;
import com.example.icecreamdb.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String getCart(Model model) {
        List<ProductBasket> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cart"; // Возвращаем имя шаблона Thymeleaf (cart.html)
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId) {
        cartService.addProduct(productId);
        return ResponseEntity.ok("Product added to cart");
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout() {
        cartService.checkout();
        return ResponseEntity.ok("Order placed successfully");
    }
}

//package com.example.icecreamdb.controller;
//
//import com.example.icecreamdb.entity.ProductBasket;
//import com.example.icecreamdb.service.CartService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @GetMapping
//    public String getCart(Model model, HttpServletRequest request) {
//        List<ProductBasket> cartItems = cartService.getCartItems(request);
//        model.addAttribute("cartItems", cartItems);
//        return "cart"; // Возвращаем имя шаблона Thymeleaf (cart.html)
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addToCart(@RequestParam Long productId, HttpServletRequest request, HttpServletResponse response) {
//        cartService.addProduct(productId, request, response);
//        return ResponseEntity.ok("Product added to cart");
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(HttpServletRequest request) {
//        cartService.checkout(request);
//        return ResponseEntity.ok("Order placed successfully");
//    }
//}

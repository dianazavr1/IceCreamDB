package com.example.icecreamdb.service;

import com.example.icecreamdb.entity.Product;
import com.example.icecreamdb.entity.ProductBasket;
import com.example.icecreamdb.entity.TotalBasket;
import com.example.icecreamdb.repo.ProductBasketRepository;
import com.example.icecreamdb.repo.ProductRepository;
import com.example.icecreamdb.repo.TotalBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductBasketRepository productBasketRepository;
    @Autowired
    private TotalBasketRepository totalBasketRepository;

    public void addProduct(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            ProductBasket item = new ProductBasket();
            item.setProductId(product.getId());
            item.setQty(1);
            item.setTotalPrice(product.getPrice());
            productBasketRepository.save(item);
        }
    }

    public List<ProductBasket> getCartItems() {
        return productBasketRepository.findAll();
    }

    public void checkout() {
        List<ProductBasket> cartItems = getCartItems();
        double total = cartItems.stream().mapToDouble(ProductBasket::getTotalPrice).sum();
        TotalBasket totalBasket = new TotalBasket();
        totalBasket.setTotalPrice(total);
        totalBasketRepository.save(totalBasket);
        productBasketRepository.deleteAll(cartItems);
    }
}

//import com.example.icecreamdb.entity.Product;
//import com.example.icecreamdb.entity.ProductBasket;
//import com.example.icecreamdb.entity.TotalBasket;
//import com.example.icecreamdb.repo.ProductBasketRepository;
//import com.example.icecreamdb.repo.ProductRepository;
//import com.example.icecreamdb.repo.TotalBasketRepository;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.util.WebUtils;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;

//@Service
//public class CartService {
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired private ProductBasketRepository productBasketRepository;
//    @Autowired private TotalBasketRepository totalBasketRepository;
//
//    private static final String COOKIE_NAME = "cart_cookie";
//
//    private String getCartCookie(HttpServletRequest request, HttpServletResponse response) {
//        Cookie cookie = WebUtils.getCookie(request, COOKIE_NAME);
//        if (cookie == null) {
//            String newCookie = UUID.randomUUID().toString();
//            response.addCookie(new Cookie(COOKIE_NAME, newCookie));
//            return newCookie;
//        }
//        return cookie.getValue();
//    }
//
//    public void addProduct(Long productId, HttpServletRequest request, HttpServletResponse response) {
//        String cartCookie = getCartCookie(request, response);
//        Optional<Product> productOpt = productRepository.findById(productId);
//        if (productOpt.isPresent()) {
//            Product product = productOpt.get();
//            ProductBasket item = new ProductBasket();
//            item.setProductId(product.getId());
//            item.setQty(1);
//            item.setTotalPrice(product.getPrice());
//            item.setCartCookie(cartCookie);
//            productBasketRepository.save(item);
//        }
//    }
//
//    public List<ProductBasket> getCartItems(HttpServletRequest request) {
//        String cartCookie = WebUtils.getCookie(request, COOKIE_NAME).getValue();
//        return productBasketRepository.findAll().stream()
//                .filter(item -> cartCookie.equals(item.getCartCookie()))
//                .toList();
//    }
//
//    public void checkout(HttpServletRequest request) {
//        String cartCookie = WebUtils.getCookie(request, COOKIE_NAME).getValue();
//        List<ProductBasket> cartItems = getCartItems(request);
//        double total = cartItems.stream().mapToDouble(ProductBasket::getTotalPrice).sum();
//        TotalBasket totalBasket = new TotalBasket();
//        totalBasket.setCartCookie(cartCookie);
//        totalBasket.setTotalPrice(total);
//        totalBasketRepository.save(totalBasket);
//        productBasketRepository.deleteAll(cartItems);
//    }
//}


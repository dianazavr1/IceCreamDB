package com.example.icecreamdb.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TotalBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;  // Общая стоимость корзины

    @OneToMany(mappedBy = "totalBasket")
    private List<ProductBasket> productBaskets;  // Список товаров в корзине

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ProductBasket> getProductBaskets() {
        return productBaskets;
    }

    public void setProductBaskets(List<ProductBasket> productBaskets) {
        this.productBaskets = productBaskets;
    }
}


package com.example.icecreamdb.entity;

import jakarta.persistence.*;

@Entity
public class ProductBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;  // Продукт, который добавлен в корзину

    private int qty;  // Количество товара

    @ManyToOne
    private TotalBasket totalBasket;  // Связь с общей корзиной

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public TotalBasket getTotalBasket() {
        return totalBasket;
    }

    public void setTotalBasket(TotalBasket totalBasket) {
        this.totalBasket = totalBasket;
    }
}


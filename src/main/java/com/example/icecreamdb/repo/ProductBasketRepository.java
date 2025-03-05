package com.example.icecreamdb.repo;

import com.example.icecreamdb.entity.Product;
import com.example.icecreamdb.entity.ProductBasket;
import com.example.icecreamdb.entity.TotalBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {
    ProductBasket findByProductAndTotalBasket(Product product, TotalBasket totalBasket);  // Получить все товары для конкретной корзины

}

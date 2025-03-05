package com.example.icecreamdb.repo;

import com.example.icecreamdb.entity.ProductBasket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {
}

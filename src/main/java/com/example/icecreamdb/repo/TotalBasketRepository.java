package com.example.icecreamdb.repo;

import com.example.icecreamdb.entity.TotalBasket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalBasketRepository extends JpaRepository<TotalBasket, Long> {
     // Получить корзину для конкретного пользователя
}

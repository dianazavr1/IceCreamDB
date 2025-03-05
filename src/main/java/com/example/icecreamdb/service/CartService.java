package com.example.icecreamdb.service;

import com.example.icecreamdb.entity.Product;
import com.example.icecreamdb.entity.ProductBasket;
import com.example.icecreamdb.entity.TotalBasket;
import com.example.icecreamdb.repo.ProductBasketRepository;
import com.example.icecreamdb.repo.ProductRepository;
import com.example.icecreamdb.repo.TotalBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final ProductBasketRepository productBasketRepository;
    private final TotalBasketRepository totalBasketRepository;

    @Autowired
    public CartService(ProductRepository productRepository,
                       ProductBasketRepository productBasketRepository,
                       TotalBasketRepository totalBasketRepository) {
        this.productRepository = productRepository;
        this.productBasketRepository = productBasketRepository;
        this.totalBasketRepository = totalBasketRepository;
    }

    public void addToCart(Long productId, int qty) {
        // Найдем продукт по его ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Получаем текущую общую корзину (или создаем новую, если её нет)
        TotalBasket totalBasket = totalBasketRepository.findById(1L)
                .orElseGet(() -> new TotalBasket()); // Например, берем первую корзину

        // Проверяем, существует ли уже продукт в корзине, если да, обновляем количество
        ProductBasket existingProductBasket = productBasketRepository.findByProductAndTotalBasket(product, totalBasket);
        if (existingProductBasket != null) {
            existingProductBasket.setQty(existingProductBasket.getQty() + qty);  // Увеличиваем количество
            productBasketRepository.save(existingProductBasket);  // Сохраняем обновленную запись
        } else {
            // Создаем новый элемент корзины
            ProductBasket productBasket = new ProductBasket();
            productBasket.setProduct(product);
            productBasket.setQty(qty);
            productBasket.setTotalBasket(totalBasket);

            // Сохраняем элемент корзины
            productBasketRepository.save(productBasket);
        }

        // Пересчитываем общую цену корзины
        updateTotalBasketPrice(totalBasket);
    }

    private void updateTotalBasketPrice(TotalBasket totalBasket) {
        double totalPrice = totalBasket.getProductBaskets().stream()
                .mapToDouble(pb -> pb.getProduct().getPrice() * pb.getQty())
                .sum();  // Считаем общую стоимость

        totalBasket.setTotalPrice(totalPrice);  // Обновляем общую стоимость корзины
        totalBasketRepository.save(totalBasket);  // Сохраняем обновленную корзину
    }
}

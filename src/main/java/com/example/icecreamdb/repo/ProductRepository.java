package com.example.icecreamdb.repo;

import com.example.icecreamdb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
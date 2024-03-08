package com.payment.example.repository;

import com.payment.example.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // No additional methods required for this example
}

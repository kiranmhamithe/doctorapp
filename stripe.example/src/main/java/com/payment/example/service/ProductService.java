package com.payment.example.service;

import com.payment.example.entity.Product;
import com.stripe.exception.StripeException;

public interface ProductService {

    Iterable<Product> getAllProducts();

    Product getProductById(Long id);

    void processPayment(Product product) throws StripeException;

    void processPayment(Long productId) throws StripeException;
}

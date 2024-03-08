package com.payment.example.controller;

import com.payment.example.entity.Product;
import com.payment.example.exception.PaymentProcessingException;
import com.payment.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //http://localhost:8080/api/products/1/process-payment
    @PostMapping("/{productId}/process-payment")
    public ResponseEntity<String> processPayment(@PathVariable Long productId) throws PaymentProcessingException {
        try {
            // Retrieve product by ID from the database
            Product product = paymentService.getProductById(productId);

            if (product != null) {
                // Process payment using Stripe API
                paymentService.processPayment(product);

                // Return a success message
                return ResponseEntity.ok("Payment processed successfully for product: " + product.getName());
            } else {
                // Product not found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle other unexpected exceptions
            throw new PaymentProcessingException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
}


package com.payment.example.service.impl;
import com.payment.example.entity.Product;
import com.payment.example.repository.ProductRepository;
import com.payment.example.service.ProductService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Autowired
    private ProductRepository productRepository;  // Assuming you have a repository for your custom Product entity

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void processPayment(Product product) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        if (product != null) {
            // Create a payment intent with Stripe
            PaymentIntent paymentIntent = PaymentIntent.create(createPaymentIntentParams(product.getPrice()));

            // You can handle the paymentIntent object as needed (e.g., extract client secret)
            System.out.println("PaymentIntent created: " + paymentIntent);

            // Implement further payment processing logic based on the paymentIntent
        } else {
            System.out.println("Product is null");
            // Handle the case where the product is null
        }
    }

    @Override
    public void processPayment(Long productId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        // Fetch product details by ID
        Product product = getProductById(productId);

        if (product != null) {
            // Create a payment intent with Stripe
            PaymentIntent paymentIntent = PaymentIntent.create(createPaymentIntentParams(product.getPrice()));

            // You can handle the paymentIntent object as needed (e.g., extract client secret)
            System.out.println("PaymentIntent created: " + paymentIntent);

            // Implement further payment processing logic based on the paymentIntent
        } else {
            System.out.println("Product not found with ID: " + productId);
        }
    }

    private Map<String, Object> createPaymentIntentParams(double price) {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (price * 100)); // Stripe uses amounts in cents
        params.put("currency", "usd");
        // Add more parameters as needed
        return params;
    }
}

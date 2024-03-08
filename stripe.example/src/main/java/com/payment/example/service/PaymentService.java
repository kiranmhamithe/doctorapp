package com.payment.example.service;
import com.payment.example.entity.Product;
import com.payment.example.repository.ProductRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Autowired
    private ProductRepository productRepository;

    // Methods for managing products (create, retrieve, etc.)
    // ...

    // Method to process payment (integration with Stripe)
    public void processPayment(Product product) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        if (product != null) {
            // Create a payment intent with Stripe
            // This is just an example, actual implementation depends on your requirements
            PaymentIntent paymentIntent = PaymentIntent.create(createPaymentIntentParams(product.getPrice()));

            // You can handle the paymentIntent object as needed (e.g., extract client secret)
            System.out.println("PaymentIntent created: " + paymentIntent);

            // Implement further payment processing logic based on the paymentIntent
        } else {
            System.out.println("Product is null");
            // Handle the case where the product is null
        }
    }

    private Map<String, Object> createPaymentIntentParams(double price) {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (price * 100)); // Stripe uses amounts in cents
        params.put("currency", "usd");
        // Add more parameters as needed
        return params;
    }

    public Product getProductById(Long productId) {
        // Implement logic to fetch product details by ID from the repository
        // For demonstration purposes, assuming you have a productRepository
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }
}

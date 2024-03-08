package com.payment.example.service.impl;

import com.payment.example.entity.Subscription;
import com.payment.example.repository.SubscriptionRepository;
import com.payment.example.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription subscribe(String description, String name, double price) {
        // Implement subscription logic, save to repository, etc.
        Subscription subscription = new Subscription();
        subscription.setDescription(description);
        subscription.setName(name);
        subscription.setPrice(price);

        // Save to repository
        return subscriptionRepository.save(subscription);
    }
}





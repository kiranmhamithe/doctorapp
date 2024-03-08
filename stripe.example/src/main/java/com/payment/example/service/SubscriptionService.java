package com.payment.example.service;

import com.payment.example.entity.Subscription;

public interface SubscriptionService {
    Subscription subscribe(String description, String name, double price);
}


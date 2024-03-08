package com.payment.example.repository;

import com.payment.example.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    // Custom query to find subscriptions by name
    List<Subscription> findByName(String name);

    // Custom query to find subscriptions by description containing a keyword
    List<Subscription> findByDescriptionContaining(String keyword);

    // Custom query to find subscriptions with a price greater than the specified amount
    List<Subscription> findByPriceGreaterThan(double amount);

    // You can add more custom queries as needed
}


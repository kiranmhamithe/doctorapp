package com.payment.example.controller;

import com.payment.example.entity.Subscription;
import com.payment.example.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public Subscription subscribe(
            @RequestParam String description,
            @RequestParam String name,
            @RequestParam double price
    )
    {
        return subscriptionService.subscribe(description, name, price);
    }


}

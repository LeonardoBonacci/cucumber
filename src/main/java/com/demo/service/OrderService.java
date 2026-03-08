package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    /**
     * Places an order. Does NOT validate item name or quantity,
     * so invalid inputs are silently accepted — this is intentional
     * to make the validation feature scenarios fail.
     */
    public String placeOrder(String item, int quantity) {
        return "ORDER-" + System.currentTimeMillis();
    }

    public String getOrderStatus(String orderId) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID must not be empty");
        }
        return "CONFIRMED";
    }
}

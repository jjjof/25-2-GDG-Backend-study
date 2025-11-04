package com.example.shop.order.dto;

//orderId, phoneNumber, address, product, date

import lombok.Getter;

@Getter
public class OrderCreateRequest {
    private Long orderId;
    private String phoneNumber;
    private String address;
    private String product;
    private String date;

    public OrderCreateRequest(Long orderId, String phoneNumber, String address, String product, String date) {
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.product = product;
        this.date = date;
    }
}

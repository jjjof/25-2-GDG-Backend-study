package com.example.shop.product.dto;

import lombok.Getter;

//productId, productName, price
@Getter
public class ProductCreateRequest {
    private String productId;
    private String productName;
    private String price;

    public ProductCreateRequest(String productId, String productName, String price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }
}

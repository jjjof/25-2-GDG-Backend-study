package com.example.shop.product.dto;


import lombok.Getter;

@Getter
public class ProductUpdateRequest {
    private String price;

    public ProductUpdateRequest(String price){
        this.price = price;
    }
}

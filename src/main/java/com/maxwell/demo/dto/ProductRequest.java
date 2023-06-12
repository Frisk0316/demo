package com.maxwell.demo.dto;

import jakarta.validation.constraints.NotNull;

public class ProductRequest {
    
    @NotNull
    private String  productName;

    @NotNull
    private String  category;

    @NotNull
    private String  imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;
    
    private String  description;

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

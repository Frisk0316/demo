package com.maxwell.demo.service;

import com.maxwell.demo.dto.ProductRequest;
import com.maxwell.demo.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
}

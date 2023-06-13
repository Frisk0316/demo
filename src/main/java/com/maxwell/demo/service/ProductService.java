package com.maxwell.demo.service;

import java.util.List;

import com.maxwell.demo.dto.ProductQueryParams;
import com.maxwell.demo.dto.ProductRequest;
import com.maxwell.demo.model.Product;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    // CRUD
    Product getProductById(Integer productId);
    
    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}

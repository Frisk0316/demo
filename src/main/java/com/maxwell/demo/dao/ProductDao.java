package com.maxwell.demo.dao;

import com.maxwell.demo.dto.ProductRequest;
import com.maxwell.demo.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}

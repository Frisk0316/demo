package com.maxwell.demo.dao;

import com.maxwell.demo.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}

package com.maxwell.demo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.maxwell.demo.dao.ProductDao;
import com.maxwell.demo.model.Product;
import com.maxwell.demo.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 1. 記得創建 DATABASE
    // 2. SQL 要分段的話記得加上空格, 不然在輸出時字串會黏在一起造成錯誤
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " 
                   + "FROM product " 
                   + "WHERE product_id = :productId";


        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        
        if(productList.size() > 0) 
        {
            return productList.get(0);
        }
        else 
        {
            return null;
        }
    }
    
}

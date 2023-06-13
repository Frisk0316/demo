package com.maxwell.demo.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.maxwell.demo.dao.ProductDao;
import com.maxwell.demo.dto.ProductQueryParams;
import com.maxwell.demo.dto.ProductRequest;
import com.maxwell.demo.model.Product;
import com.maxwell.demo.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id, " +
                            "product_name, " +
                            "category, " +
                            "image_url, " +
                            "price, " +
                            "stock, " +
                            "description, " +
                            "created_date, " +
                            "last_modified_date " +
                     "FROM product " + 
                     "WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        // 在加上 WHERE 1=1 後，下面的查詢條件便可以直接拼在 sql 後面
        // 如此做法可以使 SQL 語法簡潔許多，是相當常見的一種用法
        if(productQueryParams.getCategory() != null) {
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory());
        }

        // 要把 "%" 寫在 map.put 裡面，不可以寫在 sql 語句裡面
        if(productQueryParams.getSearch() != null) {
            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        // 排序
        // 實作時只能使用字串拼接的方式實現 ORDER BY 的語法
        // 注意要在前方加上空格
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    // 1. 記得創建 DATABASE
    // 2. SQL 要分段的話記得加上空格, 不然在輸出時字串會黏在一起造成錯誤
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, " +
                            "product_name, " +
                            "category, " +
                            "image_url, " +
                            "price, " +
                            "stock, " +
                            "description, " +
                            "created_date, " +
                            "last_modified_date " +
                     "FROM product " +
                     "WHERE product_id = :productId";


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
    
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                     "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        
        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product " + 
                     "SET product_name = :productName, " +
                         "category = :category, " +
                         "image_url = :imageUrl, " +
                         "price = :price, " +
                         "stock = :stock, " +
                         "description = :description, " +
                         "last_modified_date = :lastModifiedDate " +
                         "WHERE product_id = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}

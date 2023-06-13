package com.maxwell.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.demo.dto.ProductQueryParams;
import com.maxwell.demo.dto.ProductRequest;
import com.maxwell.demo.model.Product;
import com.maxwell.demo.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    
    // 如此使得 category 變成必選參數
    // 但是在一般情況中， category 理論上不應為必選
    // 因此要在 category 前加上 @RequestParam(required = false)
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
        // Filtering
        @RequestParam(required = false) String category, 
        @RequestParam(required = false) String search,
        
        // Sorting
        @RequestParam(defaultValue = "created_date") String orderBy,
        @RequestParam(defaultValue = "desc") String sort, 

        // Pagination
        // limit: 一次秀出幾筆數據, 對應 LIMIT
        // offset: 前方要跳過的數據, 對應 OFFSET
        // 要在前方加上 @Validated, @Max 與 @Min 才會生效
        @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
        @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        // 使用 productqueryparams 儲存參數, 如此在 controller 便只需傳遞一個參數
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParams);
    
        // 無論結果為何, 都要回傳 200 OK
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
     
        Product product = productService.getProductById(productId);

        // 若查詢單項查不到結果, 則回傳 404 NOT FOUND
        if(product != null) 
        {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        else 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        // Check if product exists
        Product product = productService.getProductById(productId);
        if(product == null) 
        { 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }                      

        // update product
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        
        // 沒必要再 check product 是否存在

        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

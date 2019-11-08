package com.shop.service;

import com.shop.domain.Product;
import com.shop.domain.ProductExample;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    List<Product> getProductList(ProductExample productExample);
    Product queryProduct(Integer id);
    int updateByPrimaryKey(Product product);
}

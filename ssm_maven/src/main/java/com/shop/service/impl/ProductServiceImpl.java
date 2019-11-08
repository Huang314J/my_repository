package com.shop.service.impl;

import com.shop.dao.ProductMapper;
import com.shop.domain.Product;
import com.shop.domain.ProductExample;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProductList() {
        // 使用逆向工程 查询数据
        List<Product> productList = productMapper.selectByExampleWithBLOBs(null);
        return productList;
    }

    public List<Product> getProductList(ProductExample productExample) {
        List<Product> productList = productMapper.selectByExampleWithBLOBs(productExample);
        return productList;
    }

    public Product queryProduct(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    public int updateByPrimaryKey(Product product) {
        int i = productMapper.updateByPrimaryKey(product);
        return i;
    }
   

}

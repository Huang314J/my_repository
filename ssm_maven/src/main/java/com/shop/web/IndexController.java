package com.shop.web;

import com.shop.domain.Product;
import com.shop.exception.MyException;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@ResponseBody
//@RestController
public class IndexController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "list")
    @ResponseBody
    public List<Product> ProductList() throws MyException {
//        int i=9/0;
        if (true)
        {
            throw new MyException("商品跑路了！");
        }

        List<Product> productList = productService.getProductList();
        return productList;
    }
    @RequestMapping(value = "/getProduct")
    public Product getProductById()
    {
        Product product = productService.queryProduct(1);
        return product;
    }

    @RequestMapping(value = "/sendJson")
    @ResponseBody
    public Product sendJson(Product product)
    {
        String name = product.getName();
        Float price = product.getPrice();
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        return p;
    }

}

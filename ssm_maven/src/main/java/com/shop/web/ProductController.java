package com.shop.web;


import com.shop.domain.Customer;
import com.shop.domain.Product;
import com.shop.domain.ProductExample;
import com.shop.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//@RequestMapping(value = "/product")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private Product product = new Product();

    @RequestMapping(value = "/productList")
    public ModelAndView productList()
    {
        ModelAndView modelAndView = new ModelAndView();
        // 查询商品列表信息
        List<Product> productList = productService.getProductList();

        // 将查询到的数据存在域中
        modelAndView.addObject("productList",productList);

        // 转发 到jsp 显示
        modelAndView.setViewName("productList");
        return modelAndView;
    }

    @RequestMapping(value = "/itemEdit.action/{id}")
    public ModelAndView productItem(@PathVariable(value = "id") String id, HttpServletRequest request)
    {
//        String id = request.getParameter("id");
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.queryProduct(Integer.valueOf(id));
        modelAndView.addObject("product",product);
        modelAndView.setViewName("productItem");
        return modelAndView;
    }

    @RequestMapping(value = "/updateItem.action")
    public String updateItem(MultipartFile pictureFile,Product product) throws IOException {
/*        product.setId(Integer.valueOf(id));
        product.setName(name);
        product.setPrice(price);
        product.setDetail(detail);*/
//        product.setCreatetime(new Date());

        if (!pictureFile.isEmpty())
        {
            String originalFilename = pictureFile.getOriginalFilename();
            String newFilename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
            File file = new File("F:\\Program Files\\IDEA_Project\\ssm\\web\\images\\"+newFilename);
            if (!file.exists())
            {
                file.mkdir();
            }
            pictureFile.transferTo(file);
            product.setPic(newFilename);
        }

        productService.updateByPrimaryKey(product);

        return "redirect:/productList";
    }

    @RequestMapping(value = "queryItem.action")
    public ModelAndView queryItem(Customer customer)
    {
        String name = customer.getProduct().getName();
        Float price = customer.getProduct().getPrice();
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andNameLike(name);
        criteria.andPriceEqualTo(price);
        List<Product> productList = productService.getProductList(productExample);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("productList");
        return modelAndView;
    }
}

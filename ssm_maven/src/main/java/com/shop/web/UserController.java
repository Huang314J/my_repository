package com.shop.web;

import com.shop.domain.Customer;
import com.shop.domain.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//@RequestMapping(value = "/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userList")
    public ModelAndView modelAndView()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.getUserList();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteAll.action")
    public String deleteAll(Customer customer)
    {
        System.out.println(customer.getIds());

        return  "redirect:/productList";
    }

    @RequestMapping(value = "/updateAll.action")
    public String updateAll(Customer customer)
    {
        System.out.print(customer);
        return  "forward:/productList";
    }
}

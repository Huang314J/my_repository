package com.shop.web;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String login(String username, String password, HttpSession session)
    {
        if (username.equals("wc")&&password.equals("wc"))
        {
            session.setAttribute("user",username);
            return "redirect:/productList";
        }
        return "login";
    }

}

package com.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysController {
    @RequestMapping(value = "tologin")
    public String tologin()
    {
        return "login";
    }


}

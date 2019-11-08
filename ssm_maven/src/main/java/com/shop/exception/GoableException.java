package com.shop.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoableException implements HandlerExceptionResolver {

	 public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
	        System.out.println(e.getMessage());
	        ModelAndView modelAndView = new ModelAndView();
	        String msg = "出错啦，速速请管理员救火！";
	        if (e instanceof MyException)
	        {
	            msg = ((MyException) e).getMsg();
	        }
	        modelAndView.addObject("msg",msg);
	        modelAndView.setViewName("error");
	        return modelAndView;
	    }
  
}

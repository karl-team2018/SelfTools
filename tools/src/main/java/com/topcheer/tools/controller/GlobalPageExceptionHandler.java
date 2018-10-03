package com.topcheer.tools.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalPageExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW ="error";
    public static final String DEFAULT_ERROR_MSG ="异常请求，请确认后，重新再试";
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",e.getMessage());
        System.out.println("error:"+e.toString());
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView dyniedErrorHandler(HttpServletRequest request,Exception e) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception","error");
        System.out.println("error:"+e.toString());
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

}

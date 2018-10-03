package com.topcheer.tools.controller;


import com.topcheer.tools.Resposity.MenuRepository;
import com.topcheer.tools.entity.MenuUrl;
import com.topcheer.tools.security.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    MenuRepository menuRepository;


    @RequestMapping("/")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("host","https://karl.com");
        return "index";//所有通过根路径访问，均进入index页面
    }

    @RequestMapping("/login")
    public String login(ModelMap modelMap){
        modelMap.addAttribute("host","https://karl.com");
        return "login";//直接访问login或这在访问须权限验证且未做过验证的资源时，自动跳转到login
    }

    @RequestMapping("/admin")
    public String admin(ModelMap modelMap){
        return "admin";
    }

    @RequestMapping("/tools")
    public String toolshome(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        System.out.println(user.getRoles());
        return "tools";
    }


}

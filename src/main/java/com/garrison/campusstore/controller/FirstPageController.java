package com.garrison.campusstore.controller;

import com.garrison.campusstore.entity.PersonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FirstPageController {

    @RequestMapping("/")
    public String firstPage(HttpServletRequest request){
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        if(user == null) return "redirect:local/login";
        else{
            if(user.getUserType() == 1) return "redirect:frontend/index";
            else if(user.getUserType() == 2) return "redirect:shopadmin/shoplist";
            else return "redirect:superadmin/main";
        }
    }

}

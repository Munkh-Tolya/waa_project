package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = {"/","/home"})
    public String homePage(Model model, HttpServletRequest request){
        model.addAttribute("categories", categoryService.getCategories());
        return "index";
    }
}

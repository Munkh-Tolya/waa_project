package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = {"/","/home"})
    public String homePage(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "index";
    }
}

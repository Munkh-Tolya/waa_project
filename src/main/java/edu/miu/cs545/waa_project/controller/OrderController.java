package edu.miu.cs545.waa_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/order")
@SessionAttributes({ "userName" })
public class OrderController {

    @GetMapping("/checkout")
    public String checkOut(){
        return "order/checkout";
    }
}

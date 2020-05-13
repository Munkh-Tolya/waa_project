package edu.miu.cs545.waa_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        System.out.println("loginError.....");
        model.addAttribute("loginError", true);
        return "account/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "account/sign-up";
    }

    @GetMapping("/sign-up/partner")
    public String signUpSeller() {
        return "account/sellerRegister";
    }

    @GetMapping("/sign-up/customer")
    public String signUpCustomer() {
        return "account/buyerRegister";
    }
}

package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

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
    public String signUp(@ModelAttribute("user") User user) {
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String createNewUser(@Valid User user, BindingResult bindingResult,Model model, @RequestParam String role) {
        User userExists = userService.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user","Email address has been registered already");
        }

        if (bindingResult.hasErrors()) {
            return "account/sign-up";
        }

        System.out.println(role);
        if (role.equals("Buyer")) {
            Buyer buyer = new Buyer();
            buyer.setFirstName(user.getFirstName());
            buyer.setLastName(user.getLastName());
            buyer.setPassword(user.getPassword());
            buyer.setEmail(user.getEmail());
            userService.save(buyer);
        } else {
            Seller seller = new Seller();
            seller.setFirstName(user.getFirstName());
            seller.setLastName(user.getLastName());
            seller.setPassword(user.getPassword());
            seller.setEmail(user.getEmail());
            userService.save(seller);
        }

        model.addAttribute("successMessage", "User has been registered successfully");
        return "account/sign-up";
    }
}

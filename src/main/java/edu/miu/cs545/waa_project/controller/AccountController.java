package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            buyer.setPassword(passwordEncoder.encode(user.getPassword()));
            buyer.setEmail(user.getEmail());
            userService.save(buyer);
        } else {
            Seller seller = new Seller();
            seller.setFirstName(user.getFirstName());
            seller.setLastName(user.getLastName());
            seller.setPassword(passwordEncoder.encode(user.getPassword()));
            seller.setEmail(user.getEmail());
            userService.save(seller);
        }

        // redirectAttributes.addFlashAttribute("successMessage", "User has been registered successfully");
        // return "redirect:/account/sign-up";
        model.addAttribute("successMessage", "User has been registered successfully");
        model.addAttribute("user", new User());
        return "account/sign-up";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        // You can redirect wherever you want, but generally it's a good practice to
        return "redirect:/account/login?logout=true";
    }
}

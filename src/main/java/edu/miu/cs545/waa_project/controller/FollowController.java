package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.service.CategoryService;
import edu.miu.cs545.waa_project.service.ItemService;
import edu.miu.cs545.waa_project.service.ProductService;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({ "userName" })
public class FollowController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/buyer/seller/{id}")
    public String getSellertById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("seller", userService.find(id));
        model.addAttribute("following",userService.isFollowing(id));
        model.addAttribute("followers",userService.getFollewersNumber(id).size());
        return "seller/sellerInfo";
    }

    @PostMapping("/buyer/seller/{id}")
    public String setAction(@PathVariable(value = "id") Long id,@RequestParam String cmd, RedirectAttributes redirectAttributes) {
        userService.addFollower(id,cmd);
        return "redirect:/buyer/seller/"+id;
    }
}
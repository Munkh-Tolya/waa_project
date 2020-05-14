package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.service.ProductReviewService;
import edu.miu.cs545.waa_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.miu.cs545.waa_project.domain.*;

@Controller
@RequestMapping("/admin")
@SessionAttributes({ "userName" })
public class AdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping("/products/{productId}/approve")
    public String approveProduct(@PathVariable("productId") Long productId, Model model) {
        Product prod= productService.find(productId);
        if (prod!= null) {
            prod.setEnabled(true);
            productService.save(prod);
        }
        model.addAttribute("product", prod);
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "/admin/products";
    }
    @PostMapping("/products/{productId}/reject")
    public String rejectProduct(@PathVariable("productId") Long productId, Model model) {
        Product prod= productService.find(productId);
        if (prod!= null) {
            prod.setEnabled(false);
            productService.save(prod);
        }
        model.addAttribute("product", prod);
        return "redirect:/admin/products";
    }

    @GetMapping("/reviews")
    public String getReview(Model model) {
        model.addAttribute("productReviews", productReviewService.getAll());
        return "/admin/reviews";
    }

    @PostMapping("/reviews/{productReviewId}/approve")
    public String approveReview(@PathVariable("productReviewId") Long productReviewId, Model model) {
        ProductReview prod= productReviewService.find(productReviewId);
        if (prod!= null) {
            prod.setEnabled(true);
            productReviewService.save(prod);
        }
        model.addAttribute("productReview", prod);
        return "redirect:/admin/reviews";
    }
    @PostMapping("/reviews/{productReviewId}/reject")
    public String rejectReview(@PathVariable("productReviewId") Long productReviewId, Model model) {
        ProductReview prod= productReviewService.find(productReviewId);
        if (prod!= null) {
            prod.setEnabled(false);
            productReviewService.save(prod);
        }
        model.addAttribute("productReview", prod);
        return "redirect:/admin/reviews";
    }
}

package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.ProductReview;
import edu.miu.cs545.waa_project.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/product/review")
@SessionAttributes({ "userName" })
public class ReviewController {
    @Autowired
    ProductReviewService productReviewService;

    @GetMapping("/{id}")
    public String addReviewForm(@ModelAttribute("review") ProductReview review, @PathVariable(value = "id") Long id) {
        return "product/add-review";
    }

    @GetMapping("/success")
    public String successReview() {
        return "product/review-detail";
    }

    @PostMapping("/{id}")
    public String saveReview(@Valid @ModelAttribute("review") ProductReview review, BindingResult bindingResult,
                             @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "product/add-review";
        }
        ProductReview prReview = productReviewService.saveReviewToProduct(review,id);
        redirectAttributes.addFlashAttribute(prReview);
        redirectAttributes.addFlashAttribute(id);
        return "redirect:/product/review/success";
    }
}

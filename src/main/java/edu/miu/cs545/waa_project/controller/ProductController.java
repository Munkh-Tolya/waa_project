package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.service.CategoryService;
import edu.miu.cs545.waa_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/product/list")
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("categories", categoryService.getCategories());
        return "product/products";
    }

    @GetMapping(value = {"/seller/addProduct"})
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getCategories());
        return "seller/addProduct";
    }

    @GetMapping("/product")
    public String getProductById(@RequestParam("id") Long id, Model model) {
        Product pr = productService.find(id);
        System.out.println(pr.getImagePath());

        model.addAttribute("product", productService.find(id));
        return "product/product";
    }

}

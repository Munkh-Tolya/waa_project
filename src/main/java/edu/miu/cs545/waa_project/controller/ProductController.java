package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.WaaProjectApplication;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.exception.InvalidImageUploadException;
import edu.miu.cs545.waa_project.service.CategoryService;
import edu.miu.cs545.waa_project.service.ProductService;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/product")
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("categories", categoryService.getCategories());
        return "product/productnew";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "product/product";
    }

    /***Product CRUD functionality for Seller: START***/
    @GetMapping("/seller/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getCategories());
        return "seller/addProduct";
    }

    @PostMapping("/seller/product")
    public String saveProduct(Product product) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller)userService.findByEmail(auth.getName());

        // Upload image part with controller level exception handler
        MultipartFile productImage = product.getProductImage();
        String uploadLocation = new ApplicationHome(WaaProjectApplication.class).getDir() + "\\static\\uploads\\";
        String imageName = "";
        if (productImage != null && !productImage.isEmpty()) {
            if (productImage.getContentType().contains("image/")) {
                System.out.println("Image is not null. " + productImage.getContentType());
                try {
                    imageName = UUID.randomUUID().toString() + "." + productImage.getOriginalFilename();
                    System.out.println(uploadLocation + imageName);
                    productImage.transferTo(new File(uploadLocation + imageName));
                    System.out.println("Image Uploaded");
                } catch (Exception e) {
                    throw new RuntimeException("Problem on saving product picture.", e);
                }
            } else {
                throw new InvalidImageUploadException();
            }
        } else {
            System.out.println("Please select image.");
        }

        product.setSeller(seller);
        product.setImagePath("uploads\\" + imageName);
        productService.save(product);
        return "redirect:/seller/product";
    }

    @ExceptionHandler(InvalidImageUploadException.class)
    public ModelAndView handleError(HttpServletRequest request, InvalidImageUploadException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidImage", exception.getFullMessage());
        mav.setViewName("image-upload-error");
        return mav;
    }

    @GetMapping("/seller/product")
    public String getProductList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller)userService.findByEmail(auth.getName());
        model.addAttribute("products", productService.getProductsBySeller(seller));
        return "/seller/productList";
    }

    /***Product CRUD functionality for Seller: END***/

}

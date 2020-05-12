package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.WaaProjectApplication;
import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.exception.InvalidImageUploadException;
import edu.miu.cs545.waa_project.service.CategoryService;
import edu.miu.cs545.waa_project.service.ItemService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private ItemService itemService;

    @GetMapping("/product")
    public String list(Model model, @RequestParam(required = false) String category) {
        if(category!=null){
            model.addAttribute("products", productService.getByCategory(Integer.parseInt(category)));
        }else{
            model.addAttribute("products", productService.getAll());
        }
        model.addAttribute("categories", categoryService.getCategories());
        return "product/products";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "product/product";
    }

    /***Product CRUD functionality for Seller: START***/
    /***Add product form*/
    @GetMapping("/seller/product/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getCategories());
        return "seller/addProduct";
    }

    /***Save new product*/
    @PostMapping("/seller/product/add")
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

    //Exception handler for image upload
    @ExceptionHandler(InvalidImageUploadException.class)
    public ModelAndView handleError(HttpServletRequest request, InvalidImageUploadException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidImage", exception.getFullMessage());
        mav.setViewName("seller/image-upload-error");
        return mav;
    }

    /***Seller product information*/
    @GetMapping("/seller/product")
    public String getProductList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller)userService.findByEmail(auth.getName());
        model.addAttribute("products", productService.getProductsBySeller(seller));
        return "/seller/productList";
    }

    /***Product delete by Seller*/
    @GetMapping("/seller/product/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        Product product = productService.find(id);
        if (product != null && itemService.findTopByProduct(product) == null){
            productService.delete(product);
        } else {
            System.out.println("Cannot delete");
        }

        return "redirect:/seller/product";
    }

    /***Product edit by Seller*/
    @GetMapping(value = {"/seller/product/{id}"})
    public String editProduct(@PathVariable(value = "id", required = false) Long id, Model model, RedirectAttributes rd) {
        if (id != null) {
            model.addAttribute("product", productService.find(id));
            model.addAttribute("categories", categoryService.getCategories());
        }
        return "seller/editProduct";
    }

    /***Product CRUD functionality for Seller: END***/

}

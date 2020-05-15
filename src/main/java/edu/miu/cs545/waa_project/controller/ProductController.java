package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.WaaProjectApplication;
import edu.miu.cs545.waa_project.domain.Category;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.exception.InvalidImageUploadException;
import edu.miu.cs545.waa_project.exception.ProductAlreadyOrderedForDeletion;
import edu.miu.cs545.waa_project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.UUID;


@Controller
@SessionAttributes({ "userName"})
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductReviewService productReviewService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @ModelAttribute
    public void commonAttributes(Model model){
        model.addAttribute("categories", categoryService.getCategories());
    }

    @GetMapping("/product")
    public String list(Model model, @RequestParam(required = false) String category) {
        if (category != null) {
            model.addAttribute("products", productService.getByCategory(Integer.parseInt(category)));
        } else {
            model.addAttribute("products", productService.getAll());
        }
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
        return "seller/addProduct";
    }

    /***Save new product*/
    @PostMapping("/seller/product/add")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(auth.getName());

        if (bindingResult.hasErrors()) {
            return "seller/addProduct";
        }

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
        mav.addObject("msg", exception.getFullMessage());
        mav.setViewName("error/exception");
        return mav;
    }

    /***Product delete by Seller*/
    @GetMapping("/seller/product/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        Product product = productService.find(id);

        if (product == null || itemService.findTopByProduct(product) != null)
            throw new ProductAlreadyOrderedForDeletion();

        productService.delete(product);
        return "redirect:/seller/product";
    }

    //Exception handler for delete product which is already ordered
    @ExceptionHandler(ProductAlreadyOrderedForDeletion.class)
    public ModelAndView handleError(HttpServletRequest request, ProductAlreadyOrderedForDeletion exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", exception.getFullMessage());
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error/exception");
        return mav;
    }

    /*** Product details by seller */
    @GetMapping(value = {"/seller/product"})
    public String productDetails(@RequestParam(value = "id", required = false) Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(auth.getName());
        if (id != null) {
            model.addAttribute("product", productService.find(id));
            return "seller/productDetails";
        }

        model.addAttribute("products", productService.getProductsBySeller(seller));
        return "/seller/productList";
    }


    /***Product edit by Seller*/
    @GetMapping(value = {"/seller/product/{id}"})
    public String editRequest(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "seller/editProduct";
    }

    // Save Product for Edit
    @PostMapping(value = {"/seller/product/{id}"})
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model, @PathVariable(value = "id", required = false) Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(auth.getName());

        if (bindingResult.hasErrors()) {
            return "seller/editProduct";
        }

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

        Category category = categoryService.getCategoryById(product.getCategory().getId());

        if (id != null) {
            Product updateProduct = productService.find(id);
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setPrice(product.getPrice());
            if (!imageName.isEmpty() && !productImage.isEmpty()) {
                updateProduct.setImagePath("uploads\\" + imageName);
                updateProduct.setProductImage(product.getProductImage());
                System.out.println("image Updated!!!!");
            }
            updateProduct.setImagePath(updateProduct.getImagePath());
            updateProduct.setQuantity(product.getQuantity());
            updateProduct.setCategory(category);
            productService.save(updateProduct);
        } else {
            product.setSeller(seller);
            product.setImagePath("uploads\\" + imageName);
            productService.save(product);
        }
        return "redirect:/seller/product";
    }
    /***Product CRUD functionality for Seller: END***/

}

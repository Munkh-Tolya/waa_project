package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.dto.ResponseDTO;
import edu.miu.cs545.waa_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("")
    public String getCartItems(){
        return "cart/cart";
    }

    @PostMapping(value = "/",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = "application/json")
    public @ResponseBody ResponseDTO saveCardItem(@RequestParam Map<String,String> params) {
        Long productId = Long.valueOf(params.get("productId"));
        int quantity = Integer.valueOf(params.get("quantity"));
        return cartService.addItem(productId,quantity);
    }
}


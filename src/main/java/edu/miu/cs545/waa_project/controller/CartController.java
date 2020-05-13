package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.dto.ResponseDTO;
import edu.miu.cs545.waa_project.service.CartService;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping(value = "/",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = "application/json")
    public @ResponseBody ResponseDTO saveCardItem(@RequestParam Map<String,String> params) {
        Long productId = Long.valueOf(params.get("productId"));
        int quantity = Integer.valueOf(params.get("quantity"));
        return cartService.addItem(productId,quantity);
    }
}


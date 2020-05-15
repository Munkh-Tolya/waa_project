package edu.miu.cs545.waa_project.controller;

import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Order;
import edu.miu.cs545.waa_project.domain.OrderStatus;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.service.CartService;
import edu.miu.cs545.waa_project.service.OrderService;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/buyer/order")
@SessionAttributes({ "userName" })
public class OrderController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @GetMapping("")
    public String getOrdersBySeller(Model model, Order order) {
        List<Order> orders = userService.getOrdersByBuyer();
        model.addAttribute("orders", orders);
        return "order/orders";
    }
    @GetMapping("/checkout")
    public String checkOut(@ModelAttribute("order") Order order,Model model,
                           @RequestParam Long sellerId ,@RequestParam(required=false) String coupon){
        model = cartService.getCheckOutSummary(model,sellerId,coupon);
        return "order/checkout";
    }

    @PostMapping("/save")
    public String saveOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult,
                            Model model, @RequestParam(required = false) String coupon, @RequestParam String sellerId,
                            RedirectAttributes redAttr){
        if (bindingResult.hasErrors()) {
            model = cartService.getCheckOutSummary(model,Long.parseLong(sellerId),coupon);
            return "order/checkout";
        }
        order = orderService.saveOrder(order,sellerId,coupon);
        if(order != null){
            redAttr.addFlashAttribute("message","Thank you! Order and coupon has been added to your account.");
            return "redirect:/buyer/order";
        }else{
             return "order/checkout";
        }
    }
    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam Long orderId,RedirectAttributes redAttr){
        redAttr.addFlashAttribute("message",orderService.cancelOrder(orderId));
        return "redirect:/buyer/order";
    }

}

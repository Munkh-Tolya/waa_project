package edu.miu.cs545.waa_project.controller;


import edu.miu.cs545.waa_project.domain.Order;
import edu.miu.cs545.waa_project.domain.OrderStatus;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.service.OrderService;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = {"/seller"})
@SessionAttributes({ "userName" })
public class SellerController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String getOrdersBySeller(Model model, Order order) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller)userService.findByEmail(auth.getName());
        List<Order> orders = userService.getOrdersBySeller(seller);
        List<OrderStatus> orderStatuses = Arrays.asList(OrderStatus.values());

        model.addAttribute("orderStatus",orderStatuses);
        model.addAttribute("tempOrder",order);
        model.addAttribute("orders", orders);
        return "seller/orders";
    }

    @PostMapping("/orders/{id}")
    public String getOrderById(Order order) {
        System.out.println(order.getId());
        System.out.println(order.getStatus());
        orderService.updateOrderStatusById(order.getId(), order.getStatus());
        return "redirect:/seller/orders";
    }

    /*** Order details by seller */
    @GetMapping(value = {"/orderList"})
    public String orderDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(auth.getName());

        model.addAttribute("orders", userService.getOrdersBySeller(seller));
        return "seller/orderList";
    }
}

package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.*;
import edu.miu.cs545.waa_project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void updateOrderStatusById(Long Id, OrderStatus status) {
        Order order = findById(Id);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public Order saveOrder(Order order, String sellerId, String coupon) {
        Buyer buyer = userService.getAuthenticatedBuyer();
        Seller seller = (Seller) userService.find(Long.parseLong(sellerId));
        if(seller != null){
            List<Item> items = cartService.getCartItemsBySeller(seller.getId());
            double result = 0.0;
            for(Item i:items){
                result += i.getPrice();
                buyer.removeCardItem(i);
            }
            if(coupon != null && buyer.getCoupon() > 0){
                result = result * 0.95;
                buyer.setCoupon(buyer.getCoupon() - 1);
            }
            order.setSeller(seller);
            order.setBuyer(buyer);
            order.setSum(result);
            order.setItems(items);
            order.setUpdateTime(new Date());
            order.setStatus(OrderStatus.New);

            userService.save(buyer);
            userService.save(seller);
            orderRepository.save(order);
            return order;
        }
        return null;
    }
}

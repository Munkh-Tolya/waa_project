package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Order;
import edu.miu.cs545.waa_project.domain.OrderStatus;
import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void updateOrderStatusById(Long Id, OrderStatus status) {
        Order order = findById(Id);
        Date date = new Date();
        order.setStatus(status);
        order.setUpdateTime(date);
        orderRepository.save(order);
    }
}

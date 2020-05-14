package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Order;
import edu.miu.cs545.waa_project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderService orderService;

    @Override
    public Order findById(Long id) {
        return orderService.findById(id);
    }
}

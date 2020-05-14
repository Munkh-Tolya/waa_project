package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Order;
import edu.miu.cs545.waa_project.domain.OrderStatus;

public interface OrderService {
    Order findById(Long id);

    void updateOrderStatusById(Long Id, OrderStatus status);

}

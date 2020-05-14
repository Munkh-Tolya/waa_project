package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Order;

public interface OrderService {
    Order findById(Long id);
}

package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Long> {
}

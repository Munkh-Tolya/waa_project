package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {
}

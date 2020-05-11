package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}

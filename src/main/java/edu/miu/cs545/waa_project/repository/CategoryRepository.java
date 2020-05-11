package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

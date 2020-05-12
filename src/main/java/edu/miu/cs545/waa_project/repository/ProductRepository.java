package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.Category;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /*** List product by seller ID ***/
    List<Product> findProductsBySeller(Seller seller);

    @Query(value = "SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findProductsByCategory(Integer categoryId);
}

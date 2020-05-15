package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findTopByProduct(Product product);

    @Query(value ="SELECT DISTINCT i FROM Buyer b " +
            "INNER JOIN b.cardItems i " +
            "WHERE b.id =:buyerId and i.product.seller.id= :sellerId")
    List<Item> getItemsBySellerId(Long buyerId, Long sellerId);
}

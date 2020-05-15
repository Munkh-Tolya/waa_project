package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find by user info by email
    public User findByEmail(String email);

    @Query(value = "SELECT b FROM Buyer b JOIN b.following s WHERE b.id = :buyerId AND s.id = :sellerId")
    Buyer isFollowing(Long buyerId, Long sellerId);

    @Query(value = "SELECT b FROM Buyer b JOIN b.following s WHERE s.id = :sellerId")
    List<Buyer> getFollowersNumber(Long sellerId);

    // Find orders by Seller
    @Query(value = "SELECT s.orders FROM Seller s WHERE s = :seller")
    List<Order> getOrdersBySeller(Seller seller);
}

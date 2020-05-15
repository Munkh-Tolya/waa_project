package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Order;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    public List<User> getAll();
    public User save(User user);
    public User find(Long id);
    public Seller addFollower(Long sellerId, String action);
    public boolean isFollowing(Long sellerId);
    public Buyer getAuthenticatedBuyer();
    public User getAuthenticatedUser();
    List<Order> getOrdersBySeller(Seller seller);
    Boolean buyerHasCoupon();
}

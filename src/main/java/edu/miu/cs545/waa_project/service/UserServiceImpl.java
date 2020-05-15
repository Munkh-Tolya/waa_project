package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Order;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() { return (List<User>) userRepository.findAll(); }

    @Override
    public User save(User user) { return userRepository.save(user); }

    @Override
    public User find(Long id) { return userRepository.findById(id).get(); }

    @Override
    public Seller addFollower(Long sellerId, String action) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Buyer buyer = (Buyer)this.findByEmail("buyer@miu.edu");
        Seller seller = (Seller) this.find(sellerId);
        if(action.equals("follow")){
            buyer.addFollowing(seller);
        }else{
            buyer.removeFollowing(seller);
        }
        userRepository.save(buyer);
        return seller;
    }

    @Override
    public boolean isFollowing(Long sellerId) {
        Buyer buyer = this.getAuthenticatedBuyer();
        Buyer result = userRepository.isFollowing(buyer.getId(),sellerId);
        if(result != null) return true;
        else return false;
    }

    @Override
    public Buyer getAuthenticatedBuyer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Buyer buyer = (Buyer)this.findByEmail(auth.getName());
        return buyer;
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.findByEmail(auth.getName());
    }

    @Override
    public List<Order> getOrdersBySeller(Seller seller) {
        return userRepository.getOrdersBySeller(seller);
    }

    @Override
    public List<Order> getOrdersByBuyer() {
        Buyer buyer = this.getAuthenticatedBuyer();
        if(buyer!=null){
            return userRepository.getOrdersByBuyer(buyer);
        }else{
            throw new NullPointerException("Buyer not found!");
        }
    }

    @Override
    public List<Buyer> getFollewersNumber(Long sellerId) {
        return userRepository.getFollowersNumber(sellerId);
    }

    @Override
    public Boolean buyerHasCoupon() {
        Buyer buyer = this.getAuthenticatedBuyer();
        if(buyer.getCoupon() > 0) return true;
        else return false;
    }

}

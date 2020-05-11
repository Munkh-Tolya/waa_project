package edu.miu.cs545.waa_project.domain;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("buyer")
public class Buyer extends User{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="card_owner_id")
    private List<Item> cardItems;

    @OneToMany
    @JoinColumn(name="buyer_id")
    private List<Order> orders;

    @ManyToMany
    private List<Seller> following;

    public Buyer(){}

    public Buyer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    public void addFollowing(Seller seller){
        following.add(seller);
    }
    public void removeFollowing(Seller seller){
        following.remove(seller);
    }

    public void removeItem(Item item) {
        cardItems.remove(item);
    }
    public void addItem(Item item) {
        cardItems.add(item);
    }

    public List<Item> getCardItems() {
        return cardItems;
    }

    public void setCardItems(List<Item> itemList) {
        this.cardItems = itemList;
    }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


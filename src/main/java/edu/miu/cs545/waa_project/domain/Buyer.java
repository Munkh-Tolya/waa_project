package edu.miu.cs545.waa_project.domain;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("buyer")
public class Buyer extends User{


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="card_owner_id")
    private List<Item> cardItems = new ArrayList<Item>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
    private List<Order> orders = new ArrayList<Order>();

    @ManyToMany
    private List<Seller> following = new ArrayList<Seller>();

    public Buyer(){
    }

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

    public void removeCardItem(Item item) {
        cardItems.remove(item);
    }
    public void addCardItem(Item item) {
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

    public List<Seller> getFollowing() {
        return following;
    }

    public void setFollowing(List<Seller> following) {
        this.following = following;
    }
}


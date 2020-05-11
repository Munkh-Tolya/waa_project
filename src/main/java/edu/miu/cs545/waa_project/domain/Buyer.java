package edu.miu.cs545.waa_project.domain;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("buyer")
public class Buyer extends User{

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Card card;

    @OneToMany
    @JoinColumn(name="buyer_id")
    private List<Order> orders;

    @ManyToMany
    private List<Seller> following;

    public Buyer(){}

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
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


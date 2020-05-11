package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private double sum;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

//    @ManyToOne
//    private Address shippingAddress;
//
//    @OneToOne
//    private PaymentInfo paymentInfo;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany
    @JoinColumn(name="order_id")
    private List<Item> items = new ArrayList<Item>();

    public Order(){}
    public Order(Date updateTime, List<Item> items) {
        this.updateTime = updateTime;
        this.status = OrderStatus.New;
        this.items = items;
        this.sum = 0.0;
        for(Item item : this.items){
            this.sum += item.getPrice();
        }
    }
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

//    public Address getShippingAddress() {
//        return shippingAddress;
//    }
//
//    public void setShippingAddress(Address shippingAddress) {
//        this.shippingAddress = shippingAddress;
//    }
//
//    public PaymentInfo getPaymentInfo() {
//        return paymentInfo;
//    }
//
//    public void setPaymentInfo(PaymentInfo paymentInfo) {
//        this.paymentInfo = paymentInfo;
//    }
}

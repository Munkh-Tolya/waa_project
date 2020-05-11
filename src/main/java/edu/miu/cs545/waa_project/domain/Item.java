package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private int quantity;

    private double price;

    @OneToOne
    private Product product;

    public Item(){}

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = quantity * product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

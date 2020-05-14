package edu.miu.cs545.waa_project.domain.dto;

import edu.miu.cs545.waa_project.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class CartSellerDTO {
    private String sellerName;
    private Long sellerId;
    private double total;
    private List<Item> items = new ArrayList<>();

    public CartSellerDTO(String sellerName, Long sellerId){
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.total = 0;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    public void addItem(Item item){
        items.add(item);
        total += item.getPrice();
    }
    public void removeItem(Item item){
        items.remove(item);
        total -= item.getPrice();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

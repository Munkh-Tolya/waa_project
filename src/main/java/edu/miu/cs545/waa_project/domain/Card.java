package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="card_id")
    private List<Item> items;
    public Card(){}

    public void removeItem(Item item) {
        items.remove(item);
    }
    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItemList() {
        return items;
    }

    public void setItemList(List<Item> itemList) {
        this.items = itemList;
    }
}

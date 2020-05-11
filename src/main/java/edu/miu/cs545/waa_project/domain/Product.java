package edu.miu.cs545.waa_project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    private String description;
    private double price;
    private String imagePath;
    private int quantity;
    private boolean enabled;

    @Transient
    @JsonIgnore
    private MultipartFile productImage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductReview> productReviewList = new ArrayList<ProductReview>();

    public Product(){}
    public Product(String name, String description, double price,
                   String imagePath, int quantity, Category category, Seller seller){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
        this.enabled = false;
        this.category = category;
        this.seller = seller;
        this.seller.addProduct(this);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public MultipartFile getProductImage() {
        return productImage;
    }
    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    public void addReview(ProductReview review){
        productReviewList.add(review);
    }
    public void removeReview(ProductReview review){
        productReviewList.remove(review);
    }
}
package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Buyer buyer;

    private int rating;
    @Lob
    private String comment;

    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public ProductReview(){}

    public ProductReview( int rating, String comment, Buyer buyer) {
        this.rating = rating;
        this.comment = comment;
        this.buyer = buyer;
        this.enabled = false;
        this.createDate = new Date();
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int star) {
        this.rating = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

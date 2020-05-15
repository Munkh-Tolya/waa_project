package edu.miu.cs545.waa_project.domain;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @CreditCardNumber
    @Size(min=16,max=16, message = "Card should have 16 numbers!")
    private String cartNumber;

    @NotEmpty
    private String nameOnCard;

    @NotNull
    private int expriyMonth;

    @NotNull
    private int expriryYear;

    public Payment(){}

    public Payment(String cartNumber, String nameOnCard, int expriyMonth, int expriryYear) {
        this.cartNumber = cartNumber;
        this.nameOnCard = nameOnCard;
        this.expriyMonth = expriyMonth;
        this.expriryYear = expriryYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public int getExpriyMonth() {
        return expriyMonth;
    }

    public void setExpriyMonth(int expriyMonth) {
        this.expriyMonth = expriyMonth;
    }

    public int getExpriryYear() {
        return expriryYear;
    }

    public void setExpriryYear(int expriryYear) {
        this.expriryYear = expriryYear;
    }
}

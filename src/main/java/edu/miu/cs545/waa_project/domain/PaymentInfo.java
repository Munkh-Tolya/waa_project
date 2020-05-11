package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue
    private Long id;

    private Long CardNumber;

    private int cvv;

    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @OneToOne
    private Address billingAddress;

    public PaymentInfo(){}

    public Long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        CardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}

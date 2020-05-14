package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartNumber;

    private String nameOnCard;

    private int expriyMonth;

    private int expriryYear;


    public PaymentInfo(){}

}

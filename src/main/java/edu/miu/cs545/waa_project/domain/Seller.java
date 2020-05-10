package edu.miu.cs545.waa_project.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("seller")
public class Seller extends User{
    public Seller(){}
}


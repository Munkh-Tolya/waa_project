package edu.miu.cs545.waa_project.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("buyer")
public class Buyer extends User{
    public Buyer(){}
}


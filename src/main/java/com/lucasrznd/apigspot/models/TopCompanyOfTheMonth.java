package com.lucasrznd.apigspot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "top_monthly_company")
public class TopCompanyOfTheMonth implements Serializable {

    @Id
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "spot_count")
    private int spotCount;

}

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
@Table(name = "top_monthly_announcers")
public class TopMonthlyAnnouncer implements Serializable {

    @Id
    @Column(name = "announcer_name")
    private String announcerName;

    @Column(name = "spot_count")
    private int spotCount;

}

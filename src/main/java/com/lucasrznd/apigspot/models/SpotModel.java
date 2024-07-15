package com.lucasrznd.apigspot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_spot")
public class SpotModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;

    @ManyToOne
    @JoinColumn(name = "announcer_id")
    private AnnouncerModel announcer;

    @Column(name = "spot_date")
    private LocalDate date;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "active_contract")
    private boolean activeContract;

    @Column(name = "price")
    private BigDecimal price;

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }

}

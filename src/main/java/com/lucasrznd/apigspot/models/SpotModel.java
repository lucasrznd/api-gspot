package com.lucasrznd.apigspot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_spot")
public class SpotModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be blank.")
    private String title;

    @NotNull(message = "Company can't be null.")
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;

    @NotNull(message = "Announcer can't be null.")
    @ManyToOne
    @JoinColumn(name = "announcer_id")
    private AnnouncerModel announcer;

    @Column(name = "spot_date")
    private LocalDate date;

    @NotNull(message = "Duration can't be blank.")
    @Column(name = "duration")
    private Double duration;

    @Column(name = "active_contract")
    private boolean activeContract;

    @Column(name = "price")
    private BigDecimal price;

}

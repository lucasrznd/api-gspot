package com.lucasrznd.apigspot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_spot")
public class Spot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Name can't be blank.")
    private String title;

    @NotBlank(message = "Company can't be blank.")
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @NotBlank(message = "Announcer can't be blank.")
    @ManyToOne
    @JoinColumn(name = "announcer_id")
    private Announcer announcer;

    @NotBlank(message = "Date can't be blank.")
    @Column(name = "date")
    private LocalDate date;

    @NotBlank(message = "Duration can't be blank.")
    @Column(name = "duration")
    private Double duration;

    @NotBlank(message = "Active Contract can't be blank.")
    @Column(name = "active_contract")
    private boolean activeContract;

    @NotBlank(message = "Price can't be blank.")
    @Column(name = "price")
    private BigDecimal price;

}

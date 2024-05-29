package com.lucasrznd.apigspot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class GenericModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "Name can't be empty.")
    @NotBlank(message = "Name can't be blank.")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "url_image", columnDefinition = "TEXT")
    private String urlImage;

}

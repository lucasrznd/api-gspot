package com.lucasrznd.apigspot.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GenericModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can't be empty.")
    @NotBlank(message = "Name can't be blank.")
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "url_image", columnDefinition = "TEXT")
    private String urlImage;

}

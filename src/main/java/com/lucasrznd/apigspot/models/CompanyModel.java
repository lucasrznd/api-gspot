package com.lucasrznd.apigspot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

@NoArgsConstructor
@Entity
@Table(name = "tb_empresa")
public class CompanyModel extends GenericModel {

    public CompanyModel(Long id, @NotEmpty(message = "Name can't be empty.") @NotBlank(message = "Name can't be blank.") String name, @NotNull String phoneNumber, @NotNull String urlImage) {
        super(id, name, phoneNumber, urlImage);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }

}

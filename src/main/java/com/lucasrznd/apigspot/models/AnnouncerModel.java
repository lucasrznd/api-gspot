package com.lucasrznd.apigspot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_locutor")
public class AnnouncerModel extends GenericModel {

    public AnnouncerModel(Long id, @NotEmpty(message = "Name can't be blank.") String name, String phoneNumber, String urlImage) {
        super(id, name, phoneNumber, urlImage);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }

}

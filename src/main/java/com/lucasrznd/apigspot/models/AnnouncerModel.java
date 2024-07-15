package com.lucasrznd.apigspot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

@NoArgsConstructor
@Entity
@Table(name = "tb_locutor")
public class AnnouncerModel extends GenericModel {

    public AnnouncerModel(Long id, String name, String phoneNumber, String urlImage) {
        super(id, name, phoneNumber, urlImage);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }

}

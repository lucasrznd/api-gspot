package com.lucasrznd.apigspot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_locutor")
public class AnnouncerModel extends GenericModel {
}

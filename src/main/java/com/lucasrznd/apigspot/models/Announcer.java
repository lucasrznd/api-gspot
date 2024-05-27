package com.lucasrznd.apigspot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_announcer")
public class Announcer extends GenericModel {
}

package com.patrick.jpasample.primary.entity.manufacturer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerProfileId;


}

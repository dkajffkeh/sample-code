package com.patrick.jpasample.primary.entity.manufacturer;

import javax.persistence.Column;
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
public class ManufacturerLanguageProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerLanguageProfileId;

    @Column
    private Long manufacturerProfileId;

    private String languageCode;
}

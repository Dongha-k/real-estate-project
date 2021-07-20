package com.project.real_estate_1.entity.residenceType;

import com.project.real_estate_1.entity.SaleType;
import com.project.real_estate_1.entity.SalesOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("O")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Officetels extends SalesOffer {
    private String floor;
}

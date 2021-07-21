package com.project.real_estate_1.entity.saleType;

import com.project.real_estate_1.entity.SalesOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale_type extends SalesOffer { // 매매
    private Long sale_price; // 매매가
}
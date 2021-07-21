package com.project.real_estate_1.entity.saleType;

import com.project.real_estate_1.entity.SalesOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
//
@Entity
@DiscriminatorValue("M")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Monthly_type extends SalesOffer { // 월세
    private Long monthly_price; // 월세 금액
    private Long monthly_deposit; // 월세 보증금
}

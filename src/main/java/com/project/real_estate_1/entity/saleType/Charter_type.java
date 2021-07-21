package com.project.real_estate_1.entity.saleType;

import com.project.real_estate_1.entity.SalesOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Charter_type extends SalesOffer { // 전세
    private Long deposit;// 전세금
}

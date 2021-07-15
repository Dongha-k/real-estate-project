package com.project.real_estate_1.entity;


import lombok.*;

import javax.persistence.*;

// 매물
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOffer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALESOFFER_ID")
    private Long id;
    private Long sale_price;
    @Embedded
    private Location location;

}
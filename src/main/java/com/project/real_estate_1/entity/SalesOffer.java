package com.project.real_estate_1.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private Long sale_price; // 매물 가격
    private Long floor; // 매물 층 수
    private Long max_floor; // 건물 총 층수
    private String zipcode; // 도로명 주소

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member; // FK

    @OneToMany(mappedBy = "salesOffer")
    private List<SalesOfferURL> salesOfferURLS;
}
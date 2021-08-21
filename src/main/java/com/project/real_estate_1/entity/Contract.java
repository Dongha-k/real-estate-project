package com.project.real_estate_1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTRACT_ID")
    private Long id;

    private String sale_type;//"월세","전세","매매"
    private String address_apartment; // 도로명 주소 + 아파트 이름
    private String purpose; // 아파트 용도
    private String area; // 전용면적 / 공급면적
    private Long sale_prices; // 매매가 / 전세금 / 보증금
    private Long monthly_prices; // 월세

    // 금액 (비율 x)
    private String provisional_down_pay; // 가계약금
    private String down_pay; // 계약금
    private String intermediate_pay; // 중도금
    private String balance; // 잔금

    private String special; // 특약사항
    private String date; // 계약날짜
    private boolean editable; // 계약서 수정가능여부

    @OneToOne
    @JoinColumn(name = "SALESOFFER_ID")
    private SalesOffer salesOffer; // 상세 매물 정보

    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Member buyer; // 계약 중 구매자

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Member seller; // 계약 중 판매자

    @ManyToOne
    @JoinColumn(name = "INTERMEDIARY_ID")
    private Member intermediary; // 계약 중 중개인
}
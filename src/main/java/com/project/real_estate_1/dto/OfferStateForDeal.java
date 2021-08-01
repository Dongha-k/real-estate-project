package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OfferStateForDeal {
    private Long idx; // 매물 번호
    private String address; // 도로명 주소
    private String residence_name; // 아파트 이름
    private Integer dong; // 동
    private Integer ho; // 호
    private String sale_type; // 월세, 전세, 매매
    private Double net_leaseable_area;//전용면적
    private Double leaseable_area;//공급면적
    private Long sale_price;//매매가/전세금/보증금
    private Long monthly_price;//월세

    private Integer provisional_down_pay_per;//가계약금 비율
    private Integer down_pay_per;//계약금 비율
    private Integer intermediate_pay_per;//중도금 비율
    private Integer balance_per;//잔금 비율
}

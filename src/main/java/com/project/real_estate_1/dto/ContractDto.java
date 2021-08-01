package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {

    private Long offerIdx;

    private String sale_type;//"월세","전세","매매"
    private String address_apartment; // 도로명 주소 + 아파트 이름
    private String purpose; // 아파트 용도
    private String area; // 전용면적 / 공급면
    private String sale_prices;
    private String monthly_price;

    // 금액 (비율 x)
    private String provisional_down_pay;
    private String down_pay;
    private String intermediate_pay;
    private String balance;

    private String special; // 특약사항

    private Long id1; // 매도인 id
    private Long id2; // 매수인 id
}
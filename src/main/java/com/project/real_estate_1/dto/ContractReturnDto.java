package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractReturnDto {
    private String sale_type;//"월세","전세","매매"
    private String address_apartment; // 도로명 주소 + 아파트 이름
    private String purpose; // 아파트 용도
    private String area; // 전용면적 / 공급면적

    private String sale_prices;
    private String monthly_prices;

    // 금액 (비율 x)
    private String provisional_down_pay;
    private String down_pay;
    private String intermediate_pay;
    private String balance;

    private String special; // 특약사항
    private String date; // 오늘날짜

    private Long id1; // 매도인 id
    private String name1; // 매도인 이름
    private String birth1; // 매도인 주번 앞자리
    private String phonenumber1; // 매도인 폰번호


    private Long id2; // 매수인 id
    private String name2; // 매수인 이름
    private String birth2; // 매수인 주번 앞자리
    private String phonenumber2; // 매수인 폰번호

    private boolean editable; // 수정가능여부
}

package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OfferDto {
    private String userId; // 작성자 유저 id

    private String apartmentName; // 아파트 명
    private String dong; // 동
    private String ho; // 호수
    private Integer net_leaseable_area; // 전용면적
    private Integer leaseable_area; // 임대면적(공급면적)

    private String type;
    // S : 매매
    // M : 월세
    // C : 전세

    private Long sale_price;

    private Long monthly_price;
    private Long monthly_deposit;

    private Long deposit;






    private Long admin_expenses; // 관리비
    private boolean contain_electric; // 관리비에 전기세 포함여부
    private boolean contain_internet;// 인터넷 여부
    private boolean contain_water; // 수도세 여부
    private boolean contain_gas; // 가스비 여부
    private boolean contain_parking; // 주차비 여부

    // 가계약금 비율 + 계약금 비율 + 중도금 비율 + 잔금 비율 = 100%
    private Integer Provisional_down_pay_per; // 가계약금 비율
    private Integer down_pay_per; // 계약금 비율
    private Integer intermediate_pay_per; // 중도금 비율
    private Integer balance_per; // 잔금 비율

    // 옵션(중문, 에어컨, 냉장고, 김치냉장고, 붙박이장 등)
    private boolean middle_door;
    private boolean air_conditioner;
    private boolean refrigerator;
    private boolean kimchi_refrigerator;
    private boolean closet;

    private String short_description; // 짧은 집 설명
    private String detail_description; // 자세한 집 설명
}
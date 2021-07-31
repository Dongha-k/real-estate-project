package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthBoardDto {
    private Long idx;
    private String residence_type; // 매물타입(A, V, O)
    private String code; // 아파트 코드
    private Integer dong; // 동
    private Integer ho; // 호수
    private Double net_leaseable_area;//전용면적
    private Double leaseable_area;//공급면적

    private String titleImg;

    private String sellerName;
    private String sellerIdNum;

    // 7 월 31일 추가
    private String address;//도로명 주소
    private String residence_name;//아파트 이름
}
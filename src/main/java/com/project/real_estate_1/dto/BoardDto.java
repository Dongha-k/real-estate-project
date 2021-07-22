package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//아파트, 이름, 동, 호수, 평, 가격
public class BoardDto {
    private Long idx; // 게시글 번호
    private String apartmentName; // 아파트 이름
    private String dong; // 동
    private String ho; // 호수
    private Integer leaseable_area; // 임대면적(공급면적)

    private String type;
    // S : 매매
    // M : 월세
    // C : 전세

    private Long sale_price;

    private Long monthly_price;
    private Long monthly_deposit;

    private Long deposit;


}

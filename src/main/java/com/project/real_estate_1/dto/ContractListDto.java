package com.project.real_estate_1.dto;

import com.project.real_estate_1.entity.OfferState;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractListDto implements Comparable<ContractListDto>{
    private String createDate; // 계약 생성일
    private Long idx; // 계약 idx
    private String residence_name; // 거주지 명
    private Integer dong; // 동
    private Integer ho; // 호
    private String residence_type; // 거주타입
    private Long sale_price; // 매매가 / 전세금 / 보증금
    private String sale_type; // 유형(매매, 전세, 보증금)
    private Long monthly_price; // 월세일때 월세
    private String sido; // 시 / 도
    private String sigungoo; // 시 / 군 / 구
    private String dongri;
    private Double leaseable_area;
    private OfferState offerState;
    private String titleImg;
    @Override
    public int compareTo(ContractListDto contractListDto) {
        return this.createDate.compareTo(contractListDto.createDate);
    }
}

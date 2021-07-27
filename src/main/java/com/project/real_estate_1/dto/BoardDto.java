package com.project.real_estate_1.dto;

import io.swagger.models.auth.In;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDto {
    private Long idx; // 게시글 번호
    private String residence_name; // 아파트 이름

    private String code; // 아파트 코드
    private Integer dong; // 동
    private Integer ho; // 호수

    private Double leaseable_area;//공급면적


    private String residence_type; // 매물타입(A, V, O)
    private String sale_type;//"월세","전세","매매"
    private Long sale_price;//매매가/전세금/보증금
    private Long monthly_price;//월세

    private String titleImg; // 리스트에서 보여줄 대표 이미지


    // 7월 27일 추가

    // 매물 위치
    private String sido;//시도
    private String sigungoo;//시군구
    private String dongri;//동리

    // 매물정보
    private String date;//사용승인일일(준공일)
    private String allnumber;//세대수
    private String parkingnumber;//총주차대수




}

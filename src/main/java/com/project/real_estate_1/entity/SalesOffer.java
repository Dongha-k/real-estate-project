package com.project.real_estate_1.entity;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 매물 정보
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesOffer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALESOFFER_ID")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private OfferState offerState;// 매물 상태

    /*
    REJECTED,       // 0 : 매물 올리기 거절됨
    UNRELIABLE,     // 1 : 직원으로부터 허위매물인지 확인안됨
    RELIABLE,       // 2 : 허위매물아닌걸로 판명
    PROVISIONAL,    // 3 : 가계약금 입금 완료
    DOWN_PAY,       // 4 : 계약금 입금 완료
    INTER_PAY,      // 5 : 중도금 입금 완료
    SOLD_OUT        // 6 : 판매 완료
    */
    private String residence_name;//아파트 이름

    private String code;//아파트 코드
    private Integer dong; // 동
    private Integer ho; // 호수
    private Double net_leaseable_area;//전용면적
    private Double leaseable_area;//공급면적

    private String residence_type;//매물 타입(A,V,O)
    private String sale_type;//"월세","전세","매매"
    private Long sale_price;//매매가/전세금/보증금
    private Long monthly_price;//월세
    private Long admin_expenses;//관리비

    private Integer provisional_down_pay_per;//가계약금 비율
    private Integer down_pay_per;//계약금 비율
    private Integer intermediate_pay_per;//중도금 비율
    private Integer balance_per;//잔금 비율

    private Integer room_num;//방 개수
    private Integer toilet_num;//욕실 개수

    private boolean middle_door;//중문
    private boolean air_conditioner;//시스템 에어컨
    private boolean refrigerator;//냉장고
    private boolean kimchi_refrigerator;//김치냉장고
    private boolean closet;//붙박이장
    private boolean oven;//빌트인 오븐
    private boolean induction;//인덕션
    private boolean airsystem;//공조기 시스템

    private boolean nego;//네고가능

    private String short_description;//짧은 집 소개
    private String long_description;//긴 집 소개
    private String apartment_description;//아파트 소개
    private String livingroom_description;//거실 소개
    private String kitchen_description;//주방 소개
    private String room1_description;//방1 소개
    private String room2_description;//방2 소개
    private String room3_description;//방3 소개
    private String toilet1_description;//화장실1 소개
    private String toilet2_description;//화장실2 소개

    private String movedate;//입주가능일


    // 매도인 멤버 정보(seller)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 7월 27일 추가

    private String address;//도로명 주소
    private String sido;//시도
    private String sigungoo;//시군구
    private String dongri;//동리
    private String date;//사용승인일일
    private Integer allnumber;//세대수
    private Integer parkingnumber;//총주차대수
    private String contact;//관리사무소 연락처
    private String porch_description; // 현관 설명

    private Integer numOfImg;

    @ElementCollection
    @CollectionTable(name = "SalesOfferURLS", joinColumns = @JoinColumn(name = "SALESOFFER_ID"))
    private List<String> salesOfferURLS = new ArrayList<>();
}
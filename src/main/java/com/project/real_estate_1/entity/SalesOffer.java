package com.project.real_estate_1.entity;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private boolean reliable; // 신뢰할 수 있는지

    private String residence_type; // 거주지 타입
    private String residence_name; // 거주지 명
    private String dong; // 동
    private String ho; // 호수

    private Integer net_leaseable_area; // 전용면적
    private Integer leaseable_area; // 임대면적(공급면적)

    private Long admin_expenses; // 관리비

    // 관리비에 포함되는 서비스
    private boolean contain_electric; // 관리비에 전기세 포함여부
    private boolean contain_internet;// 인터넷 여부
    private boolean contain_water; // 수도세 여부
    private boolean contain_gas; // 가스비 여부
    private boolean contain_parking; // 주차비 여부



    // 가계약금 비율 + 계약금 비율 + 중도금 비율 + 잔금 비율 = 100%
    private Integer provisional_down_pay_per; // 가계약금 비율
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


    private String type;
    // S : 매매
    // M : 월세
    // C : 전세


    private Long sale_price; // 매매일때 매매가

    private Long monthly_price; // 월세일 때 월세
    private Long monthly_deposit; // 월세일 때 보증금

    private Long deposit; // 전세일 때 전세금

    private Integer numOfImg; // 이미지 수

    // 글작성한 멤버 정보
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_ID")
    private Member member; // FK


    @ElementCollection
    @CollectionTable(name = "SalesOfferURLS", joinColumns = @JoinColumn(name = "SALESOFFER_ID"))
    private List<String> salesOfferURLS = new ArrayList<>();
}
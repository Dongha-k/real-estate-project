package com.project.real_estate_1.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

// 매물 정보
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SALE_TYPE")
@ToString
public abstract class SalesOffer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALESOFFER_ID")
    private Long id;

    private String apartmentName; // 아파트 명
    private String dong; // 동
    private String ho; // 호수
    private Integer net_leaseable_area; // 전용면적
    private Integer leaseable_area; // 임대면적(공급면적)

    private Long admin_expenses; // 관리비

    private String type;


    @ElementCollection
    @CollectionTable(name = "COVER_SERVICE", joinColumns = @JoinColumn(name = "SALESOFFER_ID"))
    private Set<Admin_type> coverable_service; // 관리비에 포함되는 서비스


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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member; // FK

    @OneToMany(mappedBy = "salesOffer")
    private List<SalesOfferURL> salesOfferURLS;
}
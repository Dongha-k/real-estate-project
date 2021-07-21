package com.project.real_estate_1.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

// 매물 정보
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn
public class SalesOffer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALESOFFER_ID")
    private Long id;

    private String apartmentName; // 아파트 이름
    private Integer dong; // 동
    private Integer ho; // 호수
    private Integer net_leaseable_area; // 전용면적
    private Integer leaseable_area; // 임대면적


    @Enumerated(EnumType.STRING)
    private SaleType saleType; // 거래 유형 : 월세, 전세, 매매

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member; // FK

    @OneToMany(mappedBy = "salesOffer")
    private List<SalesOfferURL> salesOfferURLS;
}
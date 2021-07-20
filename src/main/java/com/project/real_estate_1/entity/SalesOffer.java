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
    private String zipcode; // 법정동 주소
    private String detailAddressDong; // 동
    private String detailAddressHo; // 호
    private Integer area; // 면적 (평수)
    @Enumerated(EnumType.STRING)
    private SaleType saleType; // 매매, 전세, 월세 인지

    private Long sale_price; // 매물 가격



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member; // FK

    @OneToMany(mappedBy = "salesOffer")
    private List<SalesOfferURL> salesOfferURLS;
}
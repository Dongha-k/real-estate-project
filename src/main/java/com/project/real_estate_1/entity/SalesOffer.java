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
    private String residence_type; // 거주지 타입
    private String residence_name; // 단지번호
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

    // 매물 자세한 정보
    private Integer roomNum; // 방갯수
    private Integer toiletNum; // 화장실 갯수
    private boolean loft; // 복층여부
    private Integer parkingNum; // 주차 가능한 차량 댓수




    // 매도인 멤버 정보(seller)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


//    // 구매희망자 멤버 정보(buyer)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "MEMBER_ID")
//    @Column(nullable = true)
//    private Member buyer;
//
//
//    // 중개인 멤버 정보
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "MEMBER_ID")
//    @Column(nullable = true)
//    private Member intermediary; // 중개인


    @ElementCollection
    @CollectionTable(name = "SalesOfferURLS", joinColumns = @JoinColumn(name = "SALESOFFER_ID"))
    private List<String> salesOfferURLS = new ArrayList<>();
}
package com.project.real_estate_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    private String userId;
    private String firebaseId;

    @JsonIgnore
    private String password;
    private MemberState qualification; // 공인중개사 신청 상태
    private String phoneNumber; // 핸드폰 번호
    private String name; // 성명
    private String nickname; // 닉네임
    private String imgUrl; // 이미지 url

    private String idNum; // 주민번호


    @OneToOne
    @JoinColumn(name = "LICENSE_ID")
    private License license;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<SalesOffer> salesOffer = new ArrayList<>(); // 해당 멤버가 올린 매물들





    @JsonIgnore
    @OneToMany(mappedBy = "buyer") // 본인이 구매진행중인 계약
    private List<Contract> buyingContract = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "seller") // 본인이 판매진행중인 계약
    private List<Contract> sellingContract = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "intermediary") // 본인이 중개중인 계약
    private List<Contract> intermediaryContract = new ArrayList<>();
}
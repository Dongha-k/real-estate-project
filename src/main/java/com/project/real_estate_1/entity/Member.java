package com.project.real_estate_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @JsonIgnore
    private String password;
    private boolean qualified; // 공인중개사 자격이 있는지 여부
    private String phoneNumber; // 핸드폰 번호
    private String name; // 성명
    private String nickname; // 닉네임

    @OneToOne
    @JoinColumn(name = "LICENSE_ID")
    private License license;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<SalesOffer> salesOffer = new ArrayList<>();
}
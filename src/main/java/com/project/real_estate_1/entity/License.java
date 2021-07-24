package com.project.real_estate_1.entity;
// 공인중개사 자격증 정보

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class License extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LICENSE_ID")
    private Long id;
    private String certificateURL; // 자격증 사진 주소
    private String certificationNumber; // 자격증 번호
    private String self_introduction; // 자기소개

    @JsonIgnore
    @OneToOne(mappedBy = "license")
    private Member member;
}
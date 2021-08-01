package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberShortInfo {
    private Long id; // 맴버 id(PK값)
    private String name; // 멤버 이름
    private String idNum; // 주민번호 앞자리
    private String phoneNUmber; // 핸드폰번호('-'빼고)
}

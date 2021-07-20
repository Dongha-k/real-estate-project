package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CertRegisterDto {
    private String userId; // 유저 아이디
    private String certificationNumber; // 자격증 번호
}
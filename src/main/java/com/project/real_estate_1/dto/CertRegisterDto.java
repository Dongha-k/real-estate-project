package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CertRegisterDto {
    private String certificateURL; // 자격증 사진 주소
    private String certificationNumber; // 자격증 번호
}

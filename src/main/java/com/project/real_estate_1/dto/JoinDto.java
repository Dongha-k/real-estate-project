package com.project.real_estate_1.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinDto {
    private String id;
    private String password;
    private String passwordConfirm;
    private String phoneNumber;
    private String name;
    private String nickname;
    private String idNum;

}
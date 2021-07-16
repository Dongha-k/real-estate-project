package com.project.real_estate_1.dto;

import lombok.*;

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
}
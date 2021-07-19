package com.project.real_estate_1.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {
    private String id;
    private String password;
}
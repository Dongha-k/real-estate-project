package com.project.real_estate_1.entity;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.LocalTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private String createDate;
    private String lastModifiedDate;
}

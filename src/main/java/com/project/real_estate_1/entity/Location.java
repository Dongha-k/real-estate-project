package com.project.real_estate_1.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Embeddable;

// 집 위치
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {
    private float posX;
    private float posY;
}

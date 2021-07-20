package com.project.real_estate_1.entity.residenceType;

import com.project.real_estate_1.entity.SalesOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Villa extends SalesOffer {
    private String floor;
}

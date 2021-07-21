package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.Admin_type;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.entity.SalesOfferURL;
import com.project.real_estate_1.entity.saleType.Charter_type;
import com.project.real_estate_1.entity.saleType.Monthly_type;
import com.project.real_estate_1.entity.saleType.Sale_type;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class WriteService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MemberService memberService;

    public SalesOffer write(OfferDto offerDto, List<String> imgUrls) throws SQLException {
        Member findMember = em.find(Member.class,
                memberService.findByUserId(offerDto.getUserId()).getId());
        Long idx = 0L;
        if(offerDto.getType().equals("S")){
            Sale_type saleType = new Sale_type();
            saleType.setSale_price(offerDto.getSale_price());
            em.persist(saleType);
            idx = saleType.getId();
        }
        else if(offerDto.getType().equals("M")){
            Monthly_type monthly_type = new Monthly_type();
            monthly_type.setMonthly_price(offerDto.getMonthly_price());
            monthly_type.setMonthly_deposit(offerDto.getMonthly_deposit());
            em.persist(monthly_type);
            idx = monthly_type.getId();
        }
        else if(offerDto.getType().equals("C")){
            Charter_type charter_type = new Charter_type();
            charter_type.setDeposit(offerDto.getDeposit());
            em.persist(charter_type);
            idx = charter_type.getId();
        }
        em.flush();
        em.clear();

        SalesOffer salesOffer = em.find(SalesOffer.class, idx);

        salesOffer.setCreateDate(LocalDateTime.now());
        salesOffer.setLastModifiedDate(LocalDateTime.now());


        salesOffer.setApartmentName(offerDto.getApartmentName());
        salesOffer.setDong(offerDto.getDong());
        salesOffer.setHo(offerDto.getHo());
        salesOffer.setNet_leaseable_area(offerDto.getNet_leaseable_area());
        salesOffer.setLeaseable_area(offerDto.getLeaseable_area());

        salesOffer.setAdmin_expenses(offerDto.getAdmin_expenses());
        // 관리비 포함 서바스
        if(offerDto.isContain_electric()) salesOffer.getCoverable_service().add(Admin_type.Electric);
        if(offerDto.isContain_internet()) salesOffer.getCoverable_service().add(Admin_type.Internet);
        if(offerDto.isContain_water()) salesOffer.getCoverable_service().add(Admin_type.Water);
        if(offerDto.isContain_gas()) salesOffer.getCoverable_service().add(Admin_type.Gas);
        if(offerDto.isContain_parking()) salesOffer.getCoverable_service().add(Admin_type.Parking);

        // 비율
        salesOffer.setProvisional_down_pay_per(salesOffer.getProvisional_down_pay_per());
        salesOffer.setDown_pay_per(salesOffer.getDown_pay_per());
        salesOffer.setIntermediate_pay_per(salesOffer.getIntermediate_pay_per());
        salesOffer.setBalance_per(salesOffer.getBalance_per());

        // 옵션
        salesOffer.setMiddle_door(salesOffer.isMiddle_door());
        salesOffer.setAir_conditioner(salesOffer.isAir_conditioner());
        salesOffer.setRefrigerator(salesOffer.isRefrigerator());
        salesOffer.setKimchi_refrigerator(salesOffer.isKimchi_refrigerator());
        salesOffer.setCloset(salesOffer.isCloset());

        salesOffer.setShort_description(salesOffer.getShort_description());
        salesOffer.setDetail_description(salesOffer.getDetail_description());


        salesOffer.setMember(findMember);

        for (String imgUrl : imgUrls) {
            SalesOfferURL salesOfferURL = new SalesOfferURL();
            salesOfferURL.setUrl(imgUrl);
            salesOfferURL.setSalesOffer(salesOffer);
            em.persist(salesOfferURL);
            salesOffer.getSalesOfferURLS().add(salesOfferURL);
        }
        return salesOffer;
    }
}
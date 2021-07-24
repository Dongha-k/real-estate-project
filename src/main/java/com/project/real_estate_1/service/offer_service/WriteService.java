package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.SalesOffer;
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
        System.out.println(offerDto.toString());
        SalesOffer salesOffer = new SalesOffer();
        em.persist(salesOffer);
        salesOffer.setCreateDate(LocalDateTime.now());
        salesOffer.setLastModifiedDate(LocalDateTime.now());

        salesOffer.setType(offerDto.getType());

        salesOffer.setSale_price(offerDto.getSale_price());
        salesOffer.setMonthly_price(offerDto.getMonthly_price());
        salesOffer.setMonthly_deposit(offerDto.getMonthly_deposit());
        salesOffer.setDeposit(offerDto.getDeposit());

        salesOffer.setResidence_name(offerDto.getResidence_name());
        salesOffer.setResidence_type(offerDto.getResidence_type());

        salesOffer.setDong(offerDto.getDong());
        salesOffer.setHo(offerDto.getHo());
        salesOffer.setNet_leaseable_area(offerDto.getNet_leaseable_area());
        salesOffer.setLeaseable_area(offerDto.getLeaseable_area());

        salesOffer.setAdmin_expenses(offerDto.getAdmin_expenses());

        // 관리비에 포함되는 요금
        salesOffer.setContain_electric(offerDto.isContain_electric());
        salesOffer.setContain_internet(offerDto.isContain_internet());
        salesOffer.setContain_water(offerDto.isContain_water());
        salesOffer.setContain_gas(offerDto.isContain_gas());
        salesOffer.setContain_parking(offerDto.isContain_parking());

        // 비율
        salesOffer.setProvisional_down_pay_per(offerDto.getProvisional_down_pay_per());
        salesOffer.setDown_pay_per(offerDto.getDown_pay_per());
        salesOffer.setIntermediate_pay_per(offerDto.getIntermediate_pay_per());
        salesOffer.setBalance_per(offerDto.getBalance_per());

        // 옵션
        salesOffer.setMiddle_door(offerDto.isMiddle_door());
        salesOffer.setAir_conditioner(offerDto.isAir_conditioner());
        salesOffer.setRefrigerator(offerDto.isRefrigerator());
        salesOffer.setKimchi_refrigerator(offerDto.isKimchi_refrigerator());
        salesOffer.setCloset(offerDto.isCloset());


        // 매물 자세한 정보
        salesOffer.setRoomNum(offerDto.getRoomNum());
        salesOffer.setToiletNum(offerDto.getToiletNum());
        salesOffer.setLoft(offerDto.isLoft());
        salesOffer.setParkingNum(offerDto.getParkingNum());


        salesOffer.setShort_description(offerDto.getShort_description());
        salesOffer.setDetail_description(offerDto.getDetail_description());
        salesOffer.setNumOfImg(imgUrls.size());
        salesOffer.setReliable(false);
        salesOffer.setMember(findMember);
        findMember.getSalesOffer().add(salesOffer);

        for (String imgUrl : imgUrls) {
            salesOffer.getSalesOfferURLS().add(imgUrl);
        }

        return salesOffer;
    }
}
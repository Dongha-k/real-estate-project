package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.OfferState;
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



        salesOffer.setResidence_name(offerDto.getResidence_name());

        salesOffer.setCode(offerDto.getCode());
        salesOffer.setDong(offerDto.getDong());
        salesOffer.setHo(offerDto.getHo());
        salesOffer.setNet_leaseable_area(offerDto.getNet_leaseable_area());
        salesOffer.setLeaseable_area(offerDto.getLeaseable_area());


        salesOffer.setRoom_num(offerDto.getRoom_num());
        salesOffer.setToilet_num(offerDto.getToilet_num());

        salesOffer.setResidence_type(offerDto.getResidence_type());
        salesOffer.setSale_type(offerDto.getSale_type());
        salesOffer.setSale_price(offerDto.getSale_price());
        salesOffer.setMonthly_price(offerDto.getMonthly_price());
        salesOffer.setAdmin_expenses(offerDto.getAdmin_expenses());


        // 비율
        salesOffer.setProvisional_down_pay_per(offerDto.getProvisional_down_pay_per());
        salesOffer.setDown_pay_per(offerDto.getDown_pay_per());
        salesOffer.setIntermediate_pay_per(offerDto.getIntermediate_pay_per());
        salesOffer.setBalance_per(offerDto.getBalance_per());

        salesOffer.setMiddle_door(offerDto.isMiddle_door());
        salesOffer.setAir_conditioner(offerDto.isAir_conditioner());
        salesOffer.setRefrigerator(offerDto.isRefrigerator());
        salesOffer.setKimchi_refrigerator(offerDto.isKimchi_refrigerator());
        salesOffer.setCloset(offerDto.isCloset());
        salesOffer.setOven(offerDto.isOven());
        salesOffer.setInduction(offerDto.isInduction());
        salesOffer.setAirsystem(offerDto.isAirsystem());

        salesOffer.setNego(offerDto.isNego());

        salesOffer.setShort_description(offerDto.getShort_description());
        salesOffer.setLong_description(offerDto.getLong_description());
        salesOffer.setApartment_description(offerDto.getApartment_description());
        salesOffer.setLivingroom_description(offerDto.getLivingroom_description());
        salesOffer.setKitchen_description(offerDto.getKitchen_description());
        salesOffer.setRoom1_description(offerDto.getRoom1_description());
        salesOffer.setRoom2_description(offerDto.getRoom2_description());
        salesOffer.setRoom3_description(offerDto.getRoom3_description());
        salesOffer.setToilet1_description(offerDto.getToilet1_description());
        salesOffer.setToilet2_description(offerDto.getToilet2_description());

        salesOffer.setMovedate(offerDto.getMovedate());

        salesOffer.setNumOfImg(imgUrls.size());
        salesOffer.setOfferState(OfferState.UNRELIABLE);
        salesOffer.setMember(findMember);
        findMember.getSalesOffer().add(salesOffer);

        for (String imgUrl : imgUrls) {
            salesOffer.getSalesOfferURLS().add(imgUrl);
        }

        return salesOffer;
    }
}
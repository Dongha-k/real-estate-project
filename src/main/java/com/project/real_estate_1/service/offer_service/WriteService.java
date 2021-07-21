package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class WriteService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MemberService memberService;

    public void write(OfferDto offerDto, List<String> imgUrls) throws SQLException {
        Member findMember = em.find(Member.class,
                memberService.findByUserId(offerDto.getUserId()).getId());

    }
}
package com.project.real_estate_1.service.member;

import com.project.real_estate_1.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Service
@Transactional
public class AlterService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MemberService memberService;

    public boolean changeNick(String userId, String newNick) throws SQLException{
        Member findMember = memberService.findByUserId(userId);
        if(findMember == null) return false;
        Member pMember = em.find(Member.class, findMember.getId());
        if(pMember == null) return false;
        pMember.setNickname(newNick);
        return true;
    }
    public boolean changePass(String userId, String newPass) throws SQLException{
        Member findMember = memberService.findByUserId(userId);
        if(findMember == null) return false;
        Member pMember = em.find(Member.class, findMember.getId());
        if(pMember == null) return false;
        pMember.setPassword(newPass);
        return true;
    }
    public boolean changePhone(String userId, String newPhone) throws SQLException{
        Member findMember = memberService.findByUserId(userId);
        if(findMember == null) return false;
        Member pMember = em.find(Member.class, findMember.getId());
        if(pMember == null) return false;
        pMember.setPhoneNumber(newPhone);
        return true;
    }

    public boolean changeImg(String userId, String newImgUrl) throws SQLException{
        Member findMember = memberService.findByUserId(userId);
        if(findMember == null) return false;
        Member pMember = em.find(Member.class, findMember.getId());
        if(pMember == null) return false;
        pMember.setImgUrl(newImgUrl);
        return true;
    }

}

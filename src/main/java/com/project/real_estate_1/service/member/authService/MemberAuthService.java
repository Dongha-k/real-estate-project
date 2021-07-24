package com.project.real_estate_1.service.member.authService;

import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.MemberState;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Service
@Transactional
public class MemberAuthService {
    @Autowired
    private MemberService memberService;

    @PersistenceContext
    private EntityManager em;


    public boolean authorizationMember(Long idx) throws SQLException {
        Member findMember = em.find(Member.class, idx);
        if(findMember == null) return false;
        if(findMember.getQualification() != MemberState.READY) return false;
        findMember.setQualification(MemberState.QUALIFIED);
        return true;
    }

    public boolean rejectMember(Long idx) throws SQLException {
        Member findMember = em.find(Member.class, idx);
        if(findMember == null) return false;
        if(findMember.getQualification() != MemberState.READY) return false;
        findMember.setQualification(MemberState.REJECTED);
        return true;
    }
}

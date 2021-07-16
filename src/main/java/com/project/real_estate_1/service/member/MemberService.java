package com.project.real_estate_1.service.member;

import com.project.real_estate_1.dto.CertRegisterDto;
import com.project.real_estate_1.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class MemberService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JoinService joinService;

    public MemberService(){
    }

    public Member findByUserId(String userId){
        Member findMember = em.createQuery("select m from Member m where m.userId = ?1", Member.class)
                .setParameter(1, userId)
                .getResultList().get(0);
        return findMember;
    }
    public List<Member> findAllMember(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public boolean registerCertification(String userId, CertRegisterDto certRegisterDto){
        // 이미 자격이 있으면, false 리턴
        // 자격이 없으면 자격 등록 후 true 리턴
        Long id = findByUserId(userId).getId();
        Member findMember = em.find(Member.class, id);
        if(findMember.isQualified() == true) return false;
        else {
            findMember.setQualified(true);
            findMember.getLicense().setCertificationNumber(certRegisterDto.getCertificationNumber());
            findMember.getLicense().setCertificateURL(certRegisterDto.getCertificateURL());
            findMember.getLicense().setImgURL(null);
            findMember.getLicense().setSelf_introduction("등록된 소개가 없습니다.");
            findMember.getLicense().setCreateDate(LocalDateTime.now());
            findMember.getLicense().setLastModifiedDate(LocalDateTime.now());
            return true;
        }
    }

}

package com.project.real_estate_1.service.member;

import com.project.real_estate_1.dto.CertRegisterDto;
import com.project.real_estate_1.entity.License;
import com.project.real_estate_1.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JoinService joinService;

    public MemberService(){
    }

    public Member findByUserId(String userId) throws SQLException{
        if(!joinService.findUser(userId)) return null;

        Optional<Member> findMember = em.createQuery("select m from Member m where m.userId = ?1", Member.class)
                .setParameter(1, userId)
                .getResultList()
                .stream()
                .findFirst();
        return findMember.get();
    }

    public List<Member> findAllMember() throws SQLException{
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public boolean registerCertification(CertRegisterDto certRegisterDto, String imgUrl) throws SQLException {
        // 이미 자격이 있으면, false 리턴
        // 자격이 없으면 자격 등록 후 true 리턴
        String userId = certRegisterDto.getUserId();
        Long id = findByUserId(userId).getId();
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember = " + findMember);
        System.out.println(certRegisterDto.toString());
        String certURL = imgUrl;
        String certNum = certRegisterDto.getCertificationNumber();

        if(findMember.isQualified() == true) return false;
        else {
            findMember.setQualified(true);

            License license = new License();
            license.setImgURL(certURL);
            license.setCreateDate(LocalDateTime.now());
            license.setLastModifiedDate(LocalDateTime.now());
            license.setSelf_introduction("소개가 등록되어 있지 않습니다.");
            license.setCertificateURL(certURL);
            license.setCertificationNumber(certNum);
            license.setMember(findMember);
            em.persist(license);
            findMember.setLicense(license);
            return true;
        }
    }

    public List<Member> findAllQualifiedMembers(){
        List<Member> QualifiedMembers = em.createQuery("select m from Member m where m.license is not null")
                .getResultList();
        return QualifiedMembers;
    }
}

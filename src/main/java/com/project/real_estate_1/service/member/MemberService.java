package com.project.real_estate_1.service.member;

import com.project.real_estate_1.dto.MemberShortInfo;
import com.project.real_estate_1.util.GetDto;
import com.project.real_estate_1.util.GetNow;
import com.project.real_estate_1.dto.BoardDto;
import com.project.real_estate_1.dto.CertRegisterDto;
import com.project.real_estate_1.entity.License;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.MemberState;
import com.project.real_estate_1.entity.SalesOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    public MemberShortInfo findByPhoneNum(String phoneNum) throws SQLException{
        Optional<Member> findMember = em.createQuery("select m from Member m where m.phoneNumber = ?1")
                .setParameter(1, phoneNum)
                .getResultList()
                .stream()
                .findFirst();
        return GetDto.convertMember(findMember.get());
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


        if(findMember.getQualification() == MemberState.READY) return false;
        if(findMember.getQualification() == MemberState.QUALIFIED) return false;
        else {
            //REJECTED 상태이거나, NONE 상태인 경우만 신청가능
            findMember.setQualification(MemberState.READY);
            License license = new License();
            license.setCreateDate(GetNow.getTime());
            license.setLastModifiedDate(GetNow.getTime());
            license.setSelf_introduction("소개가 등록되어 있지 않습니다.");
            license.setCertificateURL(certURL);

            if(findMember.getLicense() != null){
                em.remove(findMember.getLicense());
            }
            license.setMember(findMember);
            findMember.setLicense(license);
            em.persist(license);
            findMember.setLicense(license);
            return true;
        }
    }

    public List<BoardDto> getListOfMember(String userId) throws SQLException{
        Member findMember = findByUserId(userId);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(SalesOffer salesOffer : findMember.getSalesOffer()){
            boardDtoList.add(GetDto.convert(salesOffer));
        }
        Collections.sort(boardDtoList, Collections.reverseOrder());

        return boardDtoList;
    }

    public List<Member> getReadyMember() throws SQLException{
        return em.createQuery("select m from Member m where m.qualification =?1")
                .setParameter(1, MemberState.READY)
                .getResultList();
    }

    public List<Member> getQualifiedMember() throws SQLException{
        return em.createQuery("select m from Member m where m.qualification =?1")
                .setParameter(1, MemberState.QUALIFIED)
                .getResultList();
    }
    public boolean findExistingMemberById(Long idx) throws SQLException{
        Member findMember = em.find(Member.class, idx);
        if(findMember == null) return false;
        else return true;
    }

}

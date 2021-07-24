package com.project.real_estate_1.service.member;

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
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

        if(findMember.getQualification() == MemberState.READY) return false;
        if(findMember.getQualification() == MemberState.QUALIFIED) return false;
        else {
            //REJECTED 상태이거나, NONE 상태인 경우만 신청가능
            findMember.setQualification(MemberState.READY);
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

    public List<BoardDto> getListOfMember(String userId) throws SQLException{
        Member findMember = findByUserId(userId);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(SalesOffer salesOffer : findMember.getSalesOffer()){
            BoardDto boardDto = new BoardDto();
            boardDto.setIdx(salesOffer.getId());

            boardDto.setResidence_name(salesOffer.getResidence_name());
            boardDto.setResidence_type(salesOffer.getResidence_type());

            boardDto.setDong(salesOffer.getDong());
            boardDto.setHo(salesOffer.getHo());
            boardDto.setLeaseable_area(salesOffer.getLeaseable_area());
            boardDto.setType(salesOffer.getType());
            boardDto.setSale_price(salesOffer.getSale_price());
            boardDto.setMonthly_deposit(salesOffer.getMonthly_deposit());
            boardDto.setMonthly_price(salesOffer.getMonthly_price());
            boardDto.setDeposit(salesOffer.getDeposit());

            if(salesOffer.getNumOfImg() >= 1) boardDto.setTitleImg(salesOffer.getSalesOfferURLS().get(0));
            else boardDto.setTitleImg("");

            boardDtoList.add(boardDto);
        }
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

}

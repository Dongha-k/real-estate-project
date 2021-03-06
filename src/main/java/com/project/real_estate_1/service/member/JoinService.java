package com.project.real_estate_1.service.member;

import com.project.real_estate_1.dto.JoinDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.MemberState;
import com.project.real_estate_1.util.GetNow;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Transactional
public class JoinService {
    @PersistenceContext
    EntityManager em;


    public JoinService() {
    }

    public boolean findUser(String userId) throws SQLException {
        List<Member> findMembers = em.createQuery("select m from Member m")
                .getResultList();
        for (Member findMember : findMembers) {
            if (findMember.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public boolean findPass(String userId, String userPass) throws SQLException {
        List<Member> findMembers = em.createQuery("select m from Member m")
                .getResultList();
        for (Member findMember : findMembers) {
            if (findMember.getUserId().equals(userId)) {
                if (findMember.getPassword().equals(userPass)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public Member joinUser(JoinDto joinDto, String imgUrl) throws SQLException {
        Member member = new Member();
        member.setUserId(joinDto.getUserId());
        member.setPassword(joinDto.getPassword());
        member.setCreateDate(GetNow.getTime());
        member.setLastModifiedDate(GetNow.getTime());
        member.setName(joinDto.getName());
        member.setPhoneNumber(joinDto.getPhoneNumber());
        member.setNickname(joinDto.getNickname());
        member.setIdNum(joinDto.getIdNum());
        member.setFirebaseId(joinDto.getFirebaseId());

        member.setQualification(MemberState.NONE);
        member.setImgUrl(imgUrl);
        em.persist(member);
        return member;
    }

    public boolean checkId(String id) {
//        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
        // ????????? ???????????????, '_'??? ????????? ???????????? ????????? ??????, ??????, '_'????????? ???????????? 5 ~ 12??? ??????
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";// ????????? ???????????????
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }

    public boolean checkPass(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        // ?????? ?????? 1??? ??????
        // ??????????????? ?????? 1??? ?????? ??????
        // ?????? ????????? ?????? 1??? ?????? ??????
        // ?????? 8??? ~ ?????? 20??? ??????
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        // ???????????? ex) 01086149799
        if (phoneNumber.length() < 10 || phoneNumber.length() > 11) {
            System.out.println("???????????? : ????????? ??????");
            return false;
        } else if (phoneNumber.charAt(0) != '0' || phoneNumber.charAt(1) != '1') {
            System.out.println("???????????? : 01??? ???????????? ??????");
            return false;
        } else {
            for (int i = 2; i < phoneNumber.length(); i++) {
                if (phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9') {
                    System.out.println("???????????? : ???????????? ????????? ??????????????? ???????????? ??????");
                    return false;
                }
            }
            return true;
        }
    }

    public boolean checkIdNum(String idNum) {
        if (idNum == null) return false;
        if (idNum.length() != 6) return false;
        for (int i = 0; i < idNum.length(); i++) {
            if (idNum.charAt(i) < '0' || idNum.charAt(i) > '9') return false;
        }
        return true;
    }
}

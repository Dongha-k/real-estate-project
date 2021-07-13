package com.project.real_estate_1.service.member;

import com.project.real_estate_1.domain.JoinForm;
import com.project.real_estate_1.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Transactional
public class JoinService{
    @PersistenceContext
    EntityManager em;


    public JoinService() {
    }

    public boolean findUser(String userId) throws SQLException{
        List<Member> findMembers = em.createQuery("select m from Member m")
                .getResultList();
        for (Member findMember : findMembers) {
            if(findMember.getUserId().equals(userId)){
                return true;
            }
        }
        return false;
    }
    public boolean findPass(String userId, String userPass) throws SQLException{
        List<Member> findMembers = em.createQuery("select m from Member m")
                .getResultList();
        for(Member findMember : findMembers){
            if(findMember.getUserId().equals(userId)){
                if(findMember.getPassword().equals(userPass)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    public void joinUser(JoinForm joinForm) throws SQLException {
        Member member = new Member();
        member.setUserId(joinForm.getId());
        member.setPassword(joinForm.getPassword());
        em.persist(member);
    }

    public boolean checkId(String id){

        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
        // 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }
    public boolean checkPass(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        // 숫자 최소 1자 포함
        // 영문대문자 최소 1자 이상 포함
        // 영문 소문자 최소 1자 이상 포함
        // 최소 8자 ~ 최대 20자 허용
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}

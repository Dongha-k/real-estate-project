package com.project.real_estate_1;

import com.project.real_estate_1.domain.JoinForm;
import com.project.real_estate_1.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JoinService {
    @PersistenceContext
    EntityManager em;

    public JoinService() {
    }

    public void joinUser(JoinForm joinForm){
        Member member = new Member();
        member.setUserId(joinForm.getId());
        member.setPassword(joinForm.getPassword());
        em.persist(member);
    }
}

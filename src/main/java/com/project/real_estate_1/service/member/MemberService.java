package com.project.real_estate_1.service.member;

import com.project.real_estate_1.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class MemberService {
    @PersistenceContext
    private EntityManager em;

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

}

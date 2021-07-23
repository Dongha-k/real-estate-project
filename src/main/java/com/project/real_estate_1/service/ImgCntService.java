package com.project.real_estate_1.service;

import com.project.real_estate_1.entity.ImgNumCount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Service
public class ImgCntService {
    @PersistenceContext
    private EntityManager em;
    public Long countUp(){
        ImgNumCount imgNumCount = em.find(ImgNumCount.class, 1L);
        if(imgNumCount == null){
            ImgNumCount imgNumCount1 = new ImgNumCount();
            imgNumCount1.setCount(1L);
            em.persist(imgNumCount1);
            return 1L;
        }
        else{
            imgNumCount.setCount(imgNumCount.getCount().longValue() + 1);
            return imgNumCount.getCount();
        }
    }
}

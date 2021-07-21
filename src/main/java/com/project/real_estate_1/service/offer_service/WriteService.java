package com.project.real_estate_1.service.offer_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class WriteService {
    @PersistenceContext
    private EntityManager em;




}
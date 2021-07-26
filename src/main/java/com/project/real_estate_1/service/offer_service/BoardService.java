package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.controller.util.GetBoardList;
import com.project.real_estate_1.dto.AuthBoardDto;
import com.project.real_estate_1.dto.BoardDto;
import com.project.real_estate_1.entity.OfferState;
import com.project.real_estate_1.entity.SalesOffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BoardService {
    @PersistenceContext
    private EntityManager em;

    @Value("${defaultHouseURL}")
    private static String defaultHouseURL;


    public SalesOffer findContentByIdx(Long idx) throws SQLException {
        return em.find(SalesOffer.class, idx);
    }

    public List<BoardDto> getListOfOffer() throws SQLException{
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<SalesOffer> salesOffers = em.createQuery("select s from SalesOffer s where s.offerState =?1")
                .setParameter(1, OfferState.RELIABLE)
                .getResultList();

        for (SalesOffer salesOffer : salesOffers) {
            boardDtoList.add(GetBoardList.convert(salesOffer));
        }
        return boardDtoList;
    }

    public List<AuthBoardDto> getUncheckedListOfOffer() throws SQLException{
        List<AuthBoardDto> authBoardDtoList = new ArrayList<>();
        List<SalesOffer> salesOffers = em.createQuery("select s from SalesOffer s where s.offerState =?1")
                .setParameter(1, OfferState.UNRELIABLE)
                .getResultList();
        for (SalesOffer salesOffer : salesOffers) {
            authBoardDtoList.add(GetBoardList.convertAuth(salesOffer));
        }
        return authBoardDtoList;
    }

    public SalesOffer authOffer(Long idx) throws SQLException{
        SalesOffer salesOffer = em.find(SalesOffer.class, idx);
        if(salesOffer.getOfferState() != OfferState.UNRELIABLE && salesOffer.getOfferState() != OfferState.REJECTED){
            return null;
        }
        salesOffer.setOfferState(OfferState.RELIABLE);
        return salesOffer;
    }

    public SalesOffer rejectOffer(Long idx) throws SQLException{
        SalesOffer salesOffer = em.find(SalesOffer.class, idx);
        if(salesOffer.getOfferState() != OfferState.UNRELIABLE && salesOffer.getOfferState() != OfferState.REJECTED){
            return null;
        }
        salesOffer.setOfferState(OfferState.REJECTED);
        return salesOffer;
    }


    public boolean deleteOffer(Long idx) throws SQLException{
        SalesOffer salesOffer = em.find(SalesOffer.class, idx);
        if(salesOffer == null) return false;
        em.remove(salesOffer);
        return true;
    }
}
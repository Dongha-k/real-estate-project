package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.dto.BoardDto;
import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.SalesOffer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {
    @PersistenceContext
    private EntityManager em;

    public SalesOffer findContentByIdx(Long idx) throws SQLException {
        return em.find(SalesOffer.class, idx);
    }

    public List<BoardDto> getListOfOffer() throws SQLException{
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<SalesOffer> salesOffers = em.createQuery("select s from SalesOffer s where s.reliable =?1")
                .setParameter(1, true)
                .getResultList();
        for (SalesOffer salesOffer : salesOffers) {
            BoardDto boardDto = new BoardDto();
            boardDto.setIdx(salesOffer.getId());

            boardDto.setResidence_type(salesOffer.getResidence_type());
            boardDto.setResidence_name(salesOffer.getResidence_name());

            boardDto.setDong(salesOffer.getDong());
            boardDto.setHo(salesOffer.getHo());
            boardDto.setLeaseable_area(salesOffer.getLeaseable_area());
            boardDto.setType(salesOffer.getType());
            boardDto.setSale_price(salesOffer.getSale_price());
            boardDto.setMonthly_deposit(salesOffer.getMonthly_deposit());
            boardDto.setMonthly_price(salesOffer.getMonthly_price());
            boardDto.setDeposit(salesOffer.getDeposit());
            if(salesOffer.getNumOfImg() >= 1){
                boardDto.setTitleImg(salesOffer.getSalesOfferURLS().get(0));
            }
            else{
                boardDto.setTitleImg("");
            }
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public List<BoardDto> getUncheckedListOfOffer() throws SQLException{
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<SalesOffer> salesOffers = em.createQuery("select s from SalesOffer s where s.reliable =?1")
                .setParameter(1, false)
                .getResultList();
        for (SalesOffer salesOffer : salesOffers) {
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

            if(salesOffer.getNumOfImg() >= 1){
                boardDto.setTitleImg(salesOffer.getSalesOfferURLS().get(0));
            }
            else{
                boardDto.setTitleImg("");
            }
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public SalesOffer authOffer(Long idx) throws SQLException{
        SalesOffer salesOffer = em.find(SalesOffer.class, idx);
        if(salesOffer == null) return null;
        salesOffer.setReliable(true);
        return salesOffer;
    }

    public boolean deleteOffer(Long idx) throws SQLException{
        SalesOffer salesOffer = em.find(SalesOffer.class, idx);
        if(salesOffer == null) return false;
        em.remove(salesOffer);
        return true;
    }
}
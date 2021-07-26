package com.project.real_estate_1.controller.util;

import com.project.real_estate_1.dto.AuthBoardDto;
import com.project.real_estate_1.dto.BoardDto;
import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.SalesOffer;
import org.springframework.beans.factory.annotation.Value;

public class GetBoardList {

    @Value("${defaultHouseURL}")
    private static String defaultHouseURL;

    public static BoardDto convert(SalesOffer salesOffer){
        BoardDto boardDto = new BoardDto();

        boardDto.setIdx(salesOffer.getId());
        boardDto.setResidence_name(salesOffer.getResidence_name());

        boardDto.setCode(salesOffer.getCode());
        boardDto.setDong(salesOffer.getDong());
        boardDto.setHo(salesOffer.getHo());

        boardDto.setLeaseable_area(salesOffer.getLeaseable_area());

        boardDto.setResidence_type(salesOffer.getResidence_type());
        boardDto.setSale_type(salesOffer.getSale_type());
        boardDto.setSale_price(salesOffer.getSale_price());
        boardDto.setMonthly_price(salesOffer.getMonthly_price());

        if(salesOffer.getNumOfImg() >= 1){
            boardDto.setTitleImg(salesOffer.getSalesOfferURLS().get(0));
        }
        else{
            boardDto.setTitleImg(defaultHouseURL);
        }
        return boardDto;
    }


    public static AuthBoardDto convertAuth(SalesOffer salesOffer){
        AuthBoardDto authBoardDto = new AuthBoardDto();
        authBoardDto.setIdx(salesOffer.getId());
        authBoardDto.setResidence_type(salesOffer.getResidence_type());
        authBoardDto.setCode(salesOffer.getCode());
        authBoardDto.setDong(salesOffer.getDong());
        authBoardDto.setHo(salesOffer.getHo());
        authBoardDto.setNet_leaseable_area(salesOffer.getNet_leaseable_area());
        authBoardDto.setLeaseable_area(salesOffer.getLeaseable_area());
        if(salesOffer.getNumOfImg() >= 1){
            authBoardDto.setTitleImg(salesOffer.getSalesOfferURLS().get(0));
        }
        else{
            authBoardDto.setTitleImg(defaultHouseURL);
        }

        authBoardDto.setSellerName(salesOffer.getMember().getName());
        authBoardDto.setSellerIdNum(salesOffer.getMember().getIdNum());
        return authBoardDto;
    }

}

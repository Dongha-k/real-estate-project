package com.project.real_estate_1.util;

import com.project.real_estate_1.dto.*;
import com.project.real_estate_1.entity.Contract;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.OfferState;
import com.project.real_estate_1.entity.SalesOffer;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;

public class GetDto {

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


        // 7 월 27일 추가
        boardDto.setSido(salesOffer.getSido());
        boardDto.setSigungoo(salesOffer.getSigungoo());
        boardDto.setDongri(salesOffer.getDongri());

        boardDto.setDate(salesOffer.getDate());
        boardDto.setAllnumber(salesOffer.getAllnumber());
        boardDto.setParkingnumber(salesOffer.getParkingnumber());
        // -----------

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



//        private String address;//도로명 주소
//        private String residence_name;//아파트 이름


        authBoardDto.setAddress(salesOffer.getAddress());
        authBoardDto.setResidence_name(salesOffer.getResidence_name());
        authBoardDto.setSellerName(salesOffer.getMember().getName());
        authBoardDto.setSellerIdNum(salesOffer.getMember().getIdNum());
        return authBoardDto;
    }


    public static MemberShortInfo convertMember(Member member){
        MemberShortInfo memberShortInfo = new MemberShortInfo();
        memberShortInfo.setId(member.getId());
        memberShortInfo.setName(member.getName());
        memberShortInfo.setIdNum(member.getIdNum());
        memberShortInfo.setPhonenumber(member.getPhoneNumber());
        return memberShortInfo;
    }

    public static OfferStateForDeal convertSalesOffer(SalesOffer salesOffer){
        OfferStateForDeal offerStateForDeal = new OfferStateForDeal();
        offerStateForDeal.setIdx(salesOffer.getId());
        offerStateForDeal.setAddress(salesOffer.getAddress());
        offerStateForDeal.setResidence_name(salesOffer.getResidence_name());
        offerStateForDeal.setDong(salesOffer.getDong());
        offerStateForDeal.setHo(salesOffer.getHo());
        offerStateForDeal.setSale_type(salesOffer.getSale_type());
        offerStateForDeal.setNet_leaseable_area(salesOffer.getNet_leaseable_area());
        offerStateForDeal.setLeaseable_area(salesOffer.getLeaseable_area());
        offerStateForDeal.setSale_price(salesOffer.getSale_price());
        offerStateForDeal.setMonthly_price(salesOffer.getMonthly_price());

        offerStateForDeal.setProvisional_down_pay_per(salesOffer.getProvisional_down_pay_per());
        offerStateForDeal.setDown_pay_per(salesOffer.getDown_pay_per());
        offerStateForDeal.setIntermediate_pay_per(salesOffer.getIntermediate_pay_per());
        offerStateForDeal.setBalance_per(salesOffer.getBalance_per());
        return offerStateForDeal;
    }

    public static ContractReturnDto convertToContractReturnDto(Contract contract){
        ContractReturnDto contractReturnDto = new ContractReturnDto();
        contractReturnDto.setSale_type(contract.getSale_type());
        contractReturnDto.setAddress_apartment(contract.getAddress_apartment());
        contractReturnDto.setPurpose(contract.getPurpose());
        contractReturnDto.setArea(contract.getArea());

        contractReturnDto.setSale_prices(contract.getSale_prices());
        contractReturnDto.setMonthly_prices(contract.getMonthly_prices());

        contractReturnDto.setProvisional_down_pay(contract.getProvisional_down_pay());
        contractReturnDto.setDown_pay(contract.getDown_pay());
        contractReturnDto.setIntermediate_pay(contract.getIntermediate_pay());
        contractReturnDto.setBalance(contract.getBalance());

        contractReturnDto.setSpecial(contract.getSpecial());
        contractReturnDto.setDate(contract.getCreateDate());

        contractReturnDto.setId1(contract.getSeller().getId());
        contractReturnDto.setName1(contract.getSeller().getName());
        contractReturnDto.setBirth1(contract.getSeller().getIdNum());
        contractReturnDto.setPhonenumber1(contract.getSeller().getPhoneNumber());


        contractReturnDto.setId2(contract.getBuyer().getId());
        contractReturnDto.setName2(contract.getBuyer().getName());
        contractReturnDto.setBirth2(contract.getBuyer().getIdNum());
        contractReturnDto.setPhonenumber2(contract.getBuyer().getPhoneNumber());

        contractReturnDto.setDate(contract.getDate());

        contractReturnDto.setEditable(contract.isEditable());
        return contractReturnDto;
    }

    public static ContractListDto convertContractListDto(Contract contract){
        ContractListDto contractListDto = new ContractListDto();
        contractListDto.setIdx(contract.getId());
        contractListDto.setResidence_name(contract.getSalesOffer().getResidence_name());
        contractListDto.setDong(contract.getSalesOffer().getDong());
        contractListDto.setHo(contract.getSalesOffer().getHo());
        contractListDto.setResidence_type(contract.getSalesOffer().getResidence_type());
        contractListDto.setSale_price(Long.parseLong(contract.getSale_prices()));
        contractListDto.setSale_type(contract.getSale_type());
        contractListDto.setMonthly_price(Long.parseLong(contract.getMonthly_prices()));
        contractListDto.setSido(contract.getSalesOffer().getSido());
        contractListDto.setSigungoo(contract.getSalesOffer().getSigungoo());
        contractListDto.setDongri(contract.getSalesOffer().getDongri());
        contractListDto.setLeaseable_area(contract.getSalesOffer().getLeaseable_area());
        return contractListDto;
    }

}

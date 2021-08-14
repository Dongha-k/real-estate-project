package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.dto.ContractDto;
import com.project.real_estate_1.dto.ContractListDto;
import com.project.real_estate_1.entity.Contract;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.OfferState;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.util.GetDto;
import com.project.real_estate_1.util.GetNow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ContractService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MemberService memberService;

    // 계약서 작성
    public Long writeContract(ContractDto contractDto) throws SQLException {
        Contract contract = new Contract();
        contract.setCreateDate(GetNow.getTime());
        contract.setLastModifiedDate(GetNow.getTime());
        contract.setSale_type(contractDto.getSale_type());
        contract.setAddress_apartment(contractDto.getAddress_apartment());
        contract.setPurpose(contractDto.getPurpose());
        contract.setArea(contractDto.getArea());
        contract.setSale_prices(contractDto.getSale_prices());
        contract.setMonthly_prices(contractDto.getMonthly_prices());

        contract.setProvisional_down_pay(contractDto.getProvisional_down_pay());
        contract.setDown_pay(contractDto.getDown_pay());
        contract.setIntermediate_pay(contractDto.getIntermediate_pay());
        contract.setBalance(contractDto.getBalance());

        contract.setSpecial(contractDto.getSpecial());
        contract.setDate(contractDto.getDate());
        contract.setEditable(true);

        SalesOffer salesOffer = em.find(SalesOffer.class, contractDto.getOfferIdx());
        if(salesOffer.getContract() != null){
            em.remove(salesOffer.getContract());
        }
        contract.setSalesOffer(salesOffer);
        salesOffer.setContract(contract);

        contract.setSeller(salesOffer.getMember());
        Member buyer = em.find(Member.class, contractDto.getId2());
        contract.setBuyer(buyer);
        contract.setIntermediary(null);

        em.persist(contract);
        return contract.getId();
    }

    public Contract findContractById(Long idx) throws SQLException{
        Contract contract = em.find(Contract.class, idx);
        if(contract == null) return null;
        else return contract;
    }

    public void afterProvisional_pay(Long idx) throws SQLException{
        Contract contract = em.find(Contract.class, idx);
        contract.setEditable(false);
        contract.getSalesOffer().setOfferState(OfferState.PROVISIONAL);
    }

    public List<ContractListDto> getContractList() throws SQLException{
        List<Contract> contractList = em.createQuery("select c from Contract c where c.salesOffer.offerState =?1")
                .setParameter(1, OfferState.PROVISIONAL)
                .getResultList();
        List<ContractListDto> contractListDtos = new ArrayList<>();
        for (Contract contract : contractList) contractListDtos.add(GetDto.convertContractListDto(contract));
        return contractListDtos;
    }

    public List<ContractListDto> getIntermediaryList(String userId) throws SQLException{
        Member findMember = memberService.findByUserId(userId);
        List<Contract> contractList = findMember.getIntermediaryContract();
        List<ContractListDto> contractListDtos = new ArrayList<>();
        for (Contract contract : contractList) {
            contractListDtos.add(GetDto.convertContractListDto(contract));
        }
        return contractListDtos;
    }
    public List<ContractListDto> getBuyingList(String userId) throws SQLException{
        Member findMember = memberService.findByUserId(userId);
        List<Contract> contractList = findMember.getBuyingContract();
        List<ContractListDto> contractListDtos = new ArrayList<>();
        for (Contract contract : contractList) {
            contractListDtos.add(GetDto.convertContractListDto(contract));
        }
        return contractListDtos;
    }
    public List<ContractListDto> getSellingList(String userId) throws SQLException{
        Member findMember = memberService.findByUserId(userId);
        List<Contract> contractList = findMember.getSellingContract();
        List<ContractListDto> contractListDtos = new ArrayList<>();
        for (Contract contract : contractList) {
            contractListDtos.add(GetDto.convertContractListDto(contract));
        }
        return contractListDtos;
    }
    public boolean connectionWithIntermediary(Long idx, String userId) throws SQLException{
        Contract contract = em.find(Contract.class, idx);
        if(contract.getIntermediary() != null) return false; // 이미 배정된 중개인이 있음
        contract.setIntermediary(em.find(Member.class, memberService.findByUserId(userId).getId()));
        return true;
    }

    public boolean provisionalToDown(Long idx) throws SQLException{
        Contract contract = em.find(Contract.class, idx);
        if(contract.getSalesOffer().getOfferState() != OfferState.PROVISIONAL) return false;
        contract.getSalesOffer().setOfferState(OfferState.DOWN_PAY);
        return true;
    }
    public boolean downToInter(Long idx) throws SQLException{
        Contract contract = em.find(Contract.class, idx);
        if(contract.getSalesOffer().getOfferState() != OfferState.DOWN_PAY) return false;
        contract.getSalesOffer().setOfferState(OfferState.INTER_PAY);
        return true;
    }
    public boolean interToSoldOut(Long idx) throws SQLException{
        Contract contract = em.find(Contract.class, idx);
        if(contract.getSalesOffer().getOfferState() != OfferState.INTER_PAY) return false;
        contract.getSalesOffer().setOfferState(OfferState.SOLD_OUT);
        return true;
    }
}
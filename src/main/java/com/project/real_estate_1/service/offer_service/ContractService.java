package com.project.real_estate_1.service.offer_service;

import com.project.real_estate_1.dto.ContractDto;
import com.project.real_estate_1.entity.Contract;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.OfferState;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.util.GetNow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Transactional
@Service
public class ContractService {

    @PersistenceContext
    private EntityManager em;

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
        contract.setSalesOffer(salesOffer);

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

}

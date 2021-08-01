package com.project.real_estate_1.controller.deal;

import com.project.real_estate_1.dto.ContractDto;
import com.project.real_estate_1.dto.ContractReturnDto;
import com.project.real_estate_1.entity.Contract;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.service.offer_service.BoardService;
import com.project.real_estate_1.service.offer_service.ContractService;
import com.project.real_estate_1.util.GetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/deal/contract")
public class ContractController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private JoinService joinService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<Long> writeHandler(@ModelAttribute ContractDto contractDto){
        HttpHeaders httpHeaders = new HttpHeaders();
        Long offerIdx = contractDto.getOfferIdx();
        Long sellerId = contractDto.getId1();
        Long buyerId = contractDto.getId2();
        Long contractIdx = null;
        try{
            if(!memberService.findExistingMemberById(buyerId)){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!memberService.findExistingMemberById(sellerId)){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!boardService.findExistingContentByIdx(offerIdx)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            contractIdx = contractService.writeContract(contractDto);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);

        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(contractIdx, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<ContractReturnDto> getHandler(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        Contract contract = null;
        try{
            contract = contractService.findContractById(idx);
            if(contract == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(GetDto.convertToContractReturnDto(contract), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/provisional-pay")
    public ResponseEntity<String> provisional_payHandler(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        Contract contract = null;
        try{
            contract = contractService.findContractById(idx);
            if(contract == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            contractService.afterProvisional_pay(idx);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("success", httpHeaders, HttpStatus.OK);
    }

}

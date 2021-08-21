package com.project.real_estate_1.controller.deal;

import com.project.real_estate_1.dto.ContractDto;
import com.project.real_estate_1.dto.ContractListDto;
import com.project.real_estate_1.dto.ContractReturnDto;
import com.project.real_estate_1.entity.Contract;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.MemberState;
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
import java.util.List;

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
        System.out.println("요청한 계약 idx: " + idx);
        HttpHeaders httpHeaders = new HttpHeaders();
        Contract contract = null;
        try{
            contract = contractService.findContractById(idx);
            if(contract == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
            contractService.afterProvisional_pay(idx);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        System.out.println("변경성공");
        return new ResponseEntity<>("true", httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/contractList")
    public ResponseEntity<List<ContractListDto>> getPossibleContractList(){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<ContractListDto> contractListDtos = null;
        try{
            contractListDtos = contractService.getContractList();
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }

        httpHeaders.add("code", "00");
        return new ResponseEntity<>(contractListDtos, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/connection")
    public ResponseEntity<String> connectHandler(@RequestParam Long idx,
                                                 @RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        Contract contract = null;
        Member findMember = null;
        try{
            if(userId == null || userId.trim().isEmpty()){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            findMember = memberService.findByUserId(userId);
            if(findMember == null){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            if(findMember.getQualification() != MemberState.QUALIFIED){
                httpHeaders.add("code", "03");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);

            }

            contract = contractService.findContractById(idx);
            if(contract == null){
                httpHeaders.add("code", "04");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            if(!contractService.connectionWithIntermediary(idx, userId)){
                httpHeaders.add("code", "05"); // 이미 배정된 중개인이 존재함
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "04"); // 이미 배정된 중개인이 존재함
        return new ResponseEntity<>("success", httpHeaders, HttpStatus.OK);

    }
}

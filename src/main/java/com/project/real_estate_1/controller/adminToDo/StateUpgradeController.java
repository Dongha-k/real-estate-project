package com.project.real_estate_1.controller.adminToDo;

import com.project.real_estate_1.entity.Contract;
import com.project.real_estate_1.service.offer_service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/auth")
public class StateUpgradeController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/ProvisionalToDown")
    public ResponseEntity<String> ProvisionalToDownHandler(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        Contract contract = null;
        try{
            contract = contractService.findContractById(idx);
            if(contract == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
            if(!contractService.provisionalToDown(idx)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
            if(contract.getIntermediary() == null){
                httpHeaders.add("code", "03");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("true", httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/DownToInter")
    public ResponseEntity<String> DownToInterHandler(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        Contract contract = null;
        try{
            contract = contractService.findContractById(idx);
            if(contract == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
            if(!contractService.downToInter(idx)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("true", httpHeaders, HttpStatus.OK);
    }


    @PostMapping("/InterToSoldOut")
    public ResponseEntity<String> InterToSoldOutHandler(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        Contract contract = null;
        try{
            contract = contractService.findContractById(idx);
            if(contract == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
            if(!contractService.interToSoldOut(idx)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("false", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("true", httpHeaders, HttpStatus.OK);
    }
}
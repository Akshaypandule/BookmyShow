package com.BookMyShow.Controller;

import com.BookMyShow.ServiceImpl.ATMServiceImpl;
import com.BookMyShow.dto.ATMDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/ATM")
public class ATMController {

    private ATMServiceImpl atmServiceImpl;


    @PostMapping
    public ResponseEntity<ATMDto> AddAtm(@RequestBody ATMDto atmDto){

        ATMDto addAtm = atmServiceImpl.addATM(atmDto);
        return new ResponseEntity<>(addAtm, HttpStatus.CREATED);
    }
}

package com.BookMyShow.ServiceImpl;

import com.BookMyShow.dto.ATMDto;
import com.BookMyShow.entity.ATM;
import com.BookMyShow.repository.ATMRepository;
import com.BookMyShow.service.ATMService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMServiceImpl implements ATMService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ATMRepository atmRepository;
    @Override
    public ATMDto addATM(ATMDto atmDto)
    {
        ATM atm = AtmDtoToATM(atmDto);
        ATM save = atmRepository.save(atm);
        return ATMtoATMDto(atm);
    }

    public ATM AtmDtoToATM (ATMDto atmDto){
        ATM atm = modelMapper.map(atmDto, ATM.class);
        return atm;
    }
    public ATMDto ATMtoATMDto(ATM atm){
        ATMDto atmdto = modelMapper.map(atm, ATMDto.class);
        return atmdto;
    }
}

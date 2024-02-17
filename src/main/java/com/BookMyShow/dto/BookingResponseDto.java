package com.BookMyShow.dto;

import com.BookMyShow.entity.ATM;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private Integer userId;
    private Integer theaterId;
    private Integer moviesId;
    private String time;
    private ATMDto atm;
}

package com.BookMyShow.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Integer id;
    private UserDto user;
    private MovieDto movie;
    private ATMDto atm;
    @JsonManagedReference
    private List<BookedSeatsDto> bookedSeats;
    private String time;
    private String location;
    private String theaterName;
    private Double price;
    private String food;
    private Double totalPrice;
    //@JsonManagedReference
//    private Map<FoodDto,Integer> foods;
}

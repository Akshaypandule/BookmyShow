package com.BookMyShow.Controller;

import com.BookMyShow.ServiceImpl.BookingServiceImpl;
import com.BookMyShow.dto.BookingDto;
import com.BookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/allBooking")
public class AllBookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBooking(){
        List<BookingDto> allBooking = bookingService.getAllBooking();
        return new ResponseEntity<>(allBooking, HttpStatus.OK);

    }
}

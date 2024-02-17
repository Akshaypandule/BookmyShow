package com.BookMyShow.Controller;

import com.BookMyShow.ServiceImpl.BookingServiceImpl;
import com.BookMyShow.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("booking/")
public class BookingControllers {

    @Autowired
    private BookingServiceImpl bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto)
    {
        BookingDto createdBooking = bookingService.createBooking(bookingDto);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }
    @GetMapping("{email}")
    public ResponseEntity<List<BookingDto>> getBookingByEmail(@PathVariable String email){
        List<BookingDto> bookingByEmail = bookingService.getBookingByEmail(email);
        return new ResponseEntity<>(bookingByEmail,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBooking(){
        List<BookingDto> allBooking = bookingService.getAllBooking();
        return new ResponseEntity<>(allBooking,HttpStatus.OK);
    }
}

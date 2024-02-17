package com.BookMyShow.service;

import com.BookMyShow.dto.*;

import java.util.List;
import java.util.Set;

public interface BookingService {

    public BookingDto createBooking(BookingDto bookingDto);

    List<BookingDto> getAllBooking();

    List<BookingDto> getBookingByEmail(String email);


}

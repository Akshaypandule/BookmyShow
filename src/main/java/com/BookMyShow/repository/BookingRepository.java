package com.BookMyShow.repository;

import com.BookMyShow.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

   List<Booking> findByTheaterNameAndLocationAndTime(String theaterName,String Location,String time);
   
}

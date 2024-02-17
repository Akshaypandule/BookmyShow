package com.BookMyShow.repository;

import com.BookMyShow.entity.BookedSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedSetRepository extends JpaRepository<BookedSeats,Integer> {
}

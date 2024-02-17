package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    @ManyToOne
    private User user;
    //    @ManyToOne
//    private Theater theater;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    // @JsonManagedReference
    private List<BookedSeats> bookedSeats;
    @ManyToOne
    private Movies movie;
    @ManyToOne
    private ATM atm;
    private String time;
    private String food;
    private String location;
    private String theaterName;
    private Double price;
    private Double totalPrice;
//    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
//    private Map<String,Integer>  foods;
}

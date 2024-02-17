package com.BookMyShow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long cardNumber;
    private String cardHolderName;
    private String cardExpire;

    private Integer cvv;
    @OneToMany(mappedBy = "atm",cascade = CascadeType.ALL)
    private List<Booking> booking;
}

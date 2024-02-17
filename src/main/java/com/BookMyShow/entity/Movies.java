package com.BookMyShow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movies
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movieName;
    private String moviePoster;
    private String category;
    private LocalDate releaseDate;
    private String genre;
    private String rating;
    private String country;
    @JsonIgnore
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Booking> bookingList=new ArrayList<>();

}

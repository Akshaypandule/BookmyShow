package com.BookMyShow.dto;

//import com.BookMyShow.entity.Theater;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer id;

   // private String id;
    @NotBlank(message = "Movies name is mandatory")
    private String movieName;
    @NotBlank(message = "Movies Poster is mandatory")
    private String moviePoster;
    @NotBlank(message = "Movies category is mandatory")
    private String category;
    @NotBlank(message = "Date category is mandatory")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;
    @NotBlank(message = "Movies genre is mandatory")
    private String genre;
    @NotBlank( message = "Movies rating is mandatory")
    private String rating;
    @NotBlank(message = "Movies Country is mandatory")
    private String country;

}

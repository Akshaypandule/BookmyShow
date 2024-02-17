package com.BookMyShow.Controller;

import com.BookMyShow.ServiceImpl.MoviesServiceImpl;
import com.BookMyShow.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Movies/Search")
public class MoviesSearchController {

    @Autowired
   private  MoviesServiceImpl moviesService;
    @GetMapping("/{letter}")
    public ResponseEntity<List<MovieDto>> getAllMovies(@PathVariable String letter){
        List<MovieDto> moviesByUsingAnyLetter = moviesService.findMoviesByUsingAnyLetter(letter);
        return new ResponseEntity<>(moviesByUsingAnyLetter, HttpStatus.OK);
    }
}

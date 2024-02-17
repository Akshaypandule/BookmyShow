package com.BookMyShow.Controller;

import com.BookMyShow.ServiceImpl.MoviesServiceImpl;
import com.BookMyShow.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesServiceImpl moviesService;
    @PostMapping
    public ResponseEntity<MovieDto> addMovies(@RequestBody MovieDto movieDto){
        MovieDto addMovies = moviesService.addMovies(movieDto);
        return new ResponseEntity<>(addMovies, HttpStatus.CREATED);
    }
    @GetMapping("/allmovies")
    public ResponseEntity<List<MovieDto>> getAllMoviesS(){
        List<MovieDto> allMovies = moviesService.getAllMovies();
        return new ResponseEntity<>(allMovies,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String deleteMovies(@PathVariable Integer id){
        moviesService.deleteMovies(id);
        return "Movies deleted Successfully";
    }
//    @GetMapping("all")
//    public ResponseEntity<List<MovieDto>> getMoviesWithTheater(){
//        List<MovieDto> moviesWithTheater = moviesService.findMoviesWithTheater();
//        return new ResponseEntity<>(moviesWithTheater,HttpStatus.OK);
//    }
}

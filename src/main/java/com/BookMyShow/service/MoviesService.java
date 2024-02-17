package com.BookMyShow.service;

import com.BookMyShow.dto.MovieDto;

import java.util.List;

public interface MoviesService {

    public MovieDto addMovies(MovieDto movieDto);

    public List<MovieDto> getAllMovies();

    public void deleteMovies(Integer id);

    public MovieDto FindMoviesById(Integer id);

    List<MovieDto> findMoviesByUsingAnyLetter(String name);

    //public List<MovieDto> findMoviesWithTheater();
}

package com.BookMyShow.repository;

import com.BookMyShow.dto.MovieDto;
//import com.BookMyShow.dto.TheaterDto;
import com.BookMyShow.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Integer>
{

//    @Query("SELECT m FROM Movies m INNER JOIN m.theaters t")
//    List<Movies> findTheaterWithMovies();

    List<Movies> findByMovieNameContaining(String name);


}

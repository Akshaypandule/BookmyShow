package com.BookMyShow.ServiceImpl;

import com.BookMyShow.dto.MovieDto;
import com.BookMyShow.entity.Movies;
import com.BookMyShow.exception.ResourceNotFound;
import com.BookMyShow.repository.MoviesRepository;
import com.BookMyShow.service.MoviesService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoviesRepository moviesRepository;

    @Transactional
    @Override
    public MovieDto addMovies(MovieDto movieDto)
    {
        Movies movies = (Movies) moviesDtoToMovies(movieDto);

        Movies saved = moviesRepository.save(movies);
        return moviesToMovieDto(saved);
    }

    @Override
    public List<MovieDto> getAllMovies() {

        List<Movies> all = moviesRepository.findAll();

        List<MovieDto> collect = all.stream().map(movies -> moviesToMovieDto(movies)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteMovies(Integer id) {

    }

    @Override
    public MovieDto FindMoviesById(Integer id) {
        Optional<Movies> byId = moviesRepository.findById(id);

        if(byId.isPresent()){
            Movies movies = byId.get();
            return moviesToMovieDto(movies);
        }
        else {
            throw new ResourceNotFound("Movies id is not Found "+id);
        }

    }

    // Fetch the movies by using any letter
    @Override
    public List<MovieDto> findMoviesByUsingAnyLetter(String name) {
        List<Movies> byMoviesNameContaining = moviesRepository.findByMovieNameContaining(name);
        List<MovieDto> moviesList = byMoviesNameContaining.stream().map(movies -> moviesToMovieDto(movies)).collect(Collectors.toList());
        if(moviesList.isEmpty()){
            throw  new ResourceNotFound("Not Movies found");
        }
        return moviesList;
    }


    public MovieDto moviesToMovieDto(Movies movies){
        MovieDto map = modelMapper.map(movies, MovieDto.class);
        return map;
    }

    public Movies moviesDtoToMovies(MovieDto movieDto){
        Movies map = modelMapper.map(movieDto, Movies.class);
        return map;
    }



//    @Override
//    public List<MovieDto> findMoviesWithTheater() {
//        return null;
//    }

    //  @Override
//    public List<MovieDto> findMoviesWithTheater() {
//        List<Movies> theaterWithMovies = moviesRepository.findTheaterWithMovies();
//        List<MovieDto> collect = theaterWithMovies.stream().map(movies -> moviesToMovieDto(movies)).collect(Collectors.toList());
//        return collect;
//    }

    // @Override
//    public void deleteMovies(Integer id) {
//        Movies movies = moviesRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Movies id not found"));
//        moviesRepository.deleteById(movies));
//    }





}

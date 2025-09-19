package com.sena.crud_basic.Movie.Controller;

import com.sena.crud_basic.Movie.DTO.MovieDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerMovie(@RequestBody MovieDTO movie) {
    ResponseDTO respuesta = movieService.save(movie);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllMovies() {
        var listaMovies = movieService.findAll();
        return new ResponseEntity<>(listaMovies, HttpStatus.OK);
    }

    @GetMapping("/{idMovie}")
    public ResponseEntity<Object> getOneMovie(@PathVariable int idMovie) {
        var movie = movieService.findById(idMovie);
        if (!movie.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchMovies(@PathVariable String filter) {
        var movieList = movieService.searchByTitleOrGender(filter);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @DeleteMapping("/{idMovie}")
    public ResponseEntity<Object> deleteMovie(@PathVariable int idMovie) {
    ResponseDTO message = movieService.deleteMovie(idMovie);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idMovie}")
    public ResponseEntity<Object> updateMovie(@PathVariable int idMovie, @RequestBody MovieDTO movie) {
    ResponseDTO respuesta = movieService.updateMovie(idMovie, movie);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
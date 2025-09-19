package com.sena.crud_basic.Movie.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.Movie.DTO.MovieDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Movie.Entity.Movie;
import com.sena.crud_basic.Movie.IRepository.IMovie;

@Service
public class MovieService {
    @Autowired
    private IMovie data;

    public ResponseDTO save(MovieDTO movieDTO) {
        if (movieDTO.getTitle().length() < 1 || movieDTO.getTitle().length() > 100) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El título debe tener entre 1 y 100 caracteres");
            return respuesta;
        }
        if (movieDTO.getDescription().length() < 1 || movieDTO.getDescription().length() > 500) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La descripción debe tener entre 1 y 500 caracteres");
            return respuesta;
        }
        if (movieDTO.getGender().length() < 1 || movieDTO.getGender().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El género debe tener entre 1 y 50 caracteres");
            return respuesta;
        }
        if (movieDTO.getDuration() <= 0) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La duración debe ser mayor a 0 minutos");
            return respuesta;
        }
        Movie movieRegister = convertToModel(movieDTO);
        data.save(movieRegister);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Película registrada correctamente");
        return respuesta;
    }

    public List<Movie> findAll() {
        return data.getActive();
    }

    public List<Movie> searchByTitleOrGender(String filter) {
        return data.searchByTitleOrGender(filter);
    }

    public Optional<Movie> findById(int idMovie) {
        return data.findById(idMovie);
    }

    public ResponseDTO deleteMovie(int idMovie) {
        Optional<Movie> movie = findById(idMovie);
        if (!movie.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La película no existe");
            return respuesta;
        }
        movie.get().setStatus(false);
        data.save(movie.get());
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Película eliminada correctamente");
        return respuesta;
    }

    public ResponseDTO updateMovie(int idMovie, MovieDTO movieDTO) {
    Optional<Movie> movieOptional = data.findById(idMovie);
    if (!movieOptional.isPresent()) {
        ResponseDTO respuesta = new ResponseDTO(
            HttpStatus.NOT_FOUND.toString(),
            "La película con ID " + idMovie + " no existe");
        return respuesta;
    }
    Movie existingMovie = movieOptional.get();
    if (movieDTO.getTitle().length() < 1 || movieDTO.getTitle().length() > 100) {
        ResponseDTO respuesta = new ResponseDTO(
            HttpStatus.BAD_REQUEST.toString(),
            "El título debe tener entre 1 y 100 caracteres");
        return respuesta;
    }
    if (movieDTO.getDescription().length() < 1 || movieDTO.getDescription().length() > 500) {
        ResponseDTO respuesta = new ResponseDTO(
            HttpStatus.BAD_REQUEST.toString(),
            "La descripción debe tener entre 1 y 500 caracteres");
        return respuesta;
    }
    if (movieDTO.getGender().length() < 1 || movieDTO.getGender().length() > 50) {
        ResponseDTO respuesta = new ResponseDTO(
            HttpStatus.BAD_REQUEST.toString(),
            "El género debe tener entre 1 y 50 caracteres");
        return respuesta;
    }
    if (movieDTO.getDuration() <= 0) {
        ResponseDTO respuesta = new ResponseDTO(
            HttpStatus.BAD_REQUEST.toString(),
            "La duración debe ser mayor a 0 minutos");
        return respuesta;
    }
    existingMovie.setTitle(movieDTO.getTitle());
    existingMovie.setDescription(movieDTO.getDescription());
    existingMovie.setGender(movieDTO.getGender());
    existingMovie.setDuration(movieDTO.getDuration());
    existingMovie.setImgUrl(movieDTO.getImgUrl());
    data.save(existingMovie);
    ResponseDTO respuesta = new ResponseDTO(
        HttpStatus.OK.toString(),
        "Película actualizada correctamente");
    return respuesta;
    }

    public MovieDTO convertToDTO(Movie movie) {
        return new MovieDTO(
                movie.getIdMovie(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getGender(),
                movie.getDuration(),
                movie.getImgUrl());
    }

    public Movie convertToModel(MovieDTO movieDTO) {
        return new Movie(
                0,
                movieDTO.getTitle(),
                movieDTO.getDescription(),
                movieDTO.getGender(),
                movieDTO.getDuration(),
                movieDTO.getImgUrl(),
                true);
    }
}
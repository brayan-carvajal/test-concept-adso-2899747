package com.sena.crud_basic.Movie.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.Movie.Entity.Movie;

public interface IMovie extends JpaRepository<Movie, Integer> {
    @Query("SELECT u FROM movie u WHERE u.status != false")
    List<Movie> getActive();

    @Query("SELECT u FROM movie u WHERE u.status != false AND (u.title LIKE %?1% OR u.gender LIKE %?1%)")
    List<Movie> searchByTitleOrGender(String filter);
}
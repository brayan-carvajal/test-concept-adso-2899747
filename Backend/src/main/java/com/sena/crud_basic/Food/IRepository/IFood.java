package com.sena.crud_basic.Food.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.Food.Entity.Food;

public interface IFood extends JpaRepository<Food, Integer> {
    @Query("SELECT u FROM food u WHERE u.status != false")
    List<Food> getActive();

    @Query("SELECT f FROM food f WHERE f.status != false AND (LOWER(f.name) LIKE LOWER(CONCAT('%', ?1, '%')) OR STR(f.price) LIKE %?1%)")
    List<Food> searchByNameOrPrice(String filter);
}
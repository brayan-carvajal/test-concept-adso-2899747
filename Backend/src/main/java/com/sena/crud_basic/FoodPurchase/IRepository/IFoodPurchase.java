package com.sena.crud_basic.FoodPurchase.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.FoodPurchase.Entity.FoodPurchase;

public interface IFoodPurchase extends JpaRepository<FoodPurchase, Integer> {
    @Query("SELECT u FROM foodpurchase u WHERE u.status != false")
    List<FoodPurchase> getActive();

    @Query("SELECT fp FROM foodpurchase fp WHERE fp.status = true")
    List<FoodPurchase> findActiveFoodPurchases();
}
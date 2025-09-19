package com.sena.crud_basic.Screening.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.Screening.Entity.Screening;

public interface IScreening extends JpaRepository<Screening, Integer> {
    @Query("SELECT u FROM screening u WHERE u.status != false")
    List<Screening> getActive();

    @Query("SELECT s FROM screening s WHERE s.status = true")
    List<Screening> findActiveScreenings();
}
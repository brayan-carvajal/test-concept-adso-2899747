package com.sena.crud_basic.Reservation.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.Reservation.Entity.Reservation;

public interface IReservation extends JpaRepository<Reservation, Integer> {
    @Query("SELECT u FROM reservation u WHERE u.status != false")
    List<Reservation> getActive();

    @Query("SELECT r FROM reservation r WHERE r.status = true")
    List<Reservation> findActiveReservations();
}
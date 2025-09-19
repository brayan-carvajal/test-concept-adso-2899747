package com.sena.crud_basic.Ticket.Repository;

import com.sena.crud_basic.Ticket.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ITicket extends JpaRepository<Ticket, Integer> {
    @Query("SELECT u FROM Ticket u WHERE u.status != false")
    List<Ticket> getActive();

    @Query("SELECT t FROM Ticket t WHERE t.status = true")
    List<Ticket> findActiveTickets();
}

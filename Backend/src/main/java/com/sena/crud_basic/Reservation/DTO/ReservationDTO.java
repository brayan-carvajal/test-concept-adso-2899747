package com.sena.crud_basic.Reservation.DTO;

import com.sena.crud_basic.Customer.Entity.Customer;
import com.sena.crud_basic.Screening.Entity.Screening;

public class ReservationDTO {
    private int idReservation;
    private Customer customer;
    private Screening screening;
    private int ticketQuantity;

    public ReservationDTO() {}

    public ReservationDTO(int idReservation, Customer customer, Screening screening, int ticketQuantity) {
        this.idReservation = idReservation;
        this.customer = customer;
        this.screening = screening;
        this.ticketQuantity = ticketQuantity;
    }

    public int getIdReservation() { return idReservation; }
    public void setIdReservation(int idReservation) { this.idReservation = idReservation; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Screening getScreening() { return screening; }
    public void setScreening(Screening screening) { this.screening = screening; }
    public int getTicketQuantity() { return ticketQuantity; }
    public void setTicketQuantity(int ticketQuantity) { this.ticketQuantity = ticketQuantity; }
}
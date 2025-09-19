package com.sena.crud_basic.Ticket.DTO;

import com.sena.crud_basic.Customer.Entity.Customer;
import com.sena.crud_basic.Screening.Entity.Screening;

public class TicketDTO {
    private int idTicket;
    private Customer idCustomer;
    private Screening idScreening;
    private double price;

    public TicketDTO() {}

    public TicketDTO(int idTicket, Customer idCustomer, Screening idScreening, double price) {
        this.idTicket = idTicket;
        this.idCustomer = idCustomer;
        this.idScreening = idScreening;
        this.price = price;
    }

    public int getIdTicket() { return idTicket; }
    public void setIdTicket(int idTicket) { this.idTicket = idTicket; }
    public Customer getCustomer() { return idCustomer; }
    public void setCustomer(Customer idCustomer) { this.idCustomer = idCustomer; }
    public Screening getScreening() { return idScreening; }
    public void setScreening(Screening idScreening) { this.idScreening = idScreening; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

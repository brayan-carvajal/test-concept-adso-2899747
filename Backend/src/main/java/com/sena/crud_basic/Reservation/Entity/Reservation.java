package com.sena.crud_basic.Reservation.Entity;

import jakarta.persistence.*;
import com.sena.crud_basic.Customer.Entity.Customer;
import com.sena.crud_basic.Screening.Entity.Screening;

@Entity(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idReservation")
	private int idReservation;

	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idScreening", nullable = false)
	private Screening screening;

	@Column(name = "ticketQuantity", nullable = false)
	private int ticketQuantity;

	@Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
	private boolean status;

	public Reservation() {}

	public Reservation(int idReservation, Customer customer, Screening screening, int ticketQuantity, boolean status) {
		this.idReservation = idReservation;
		this.customer = customer;
		this.screening = screening;
		this.ticketQuantity = ticketQuantity;
		this.status = status;
	}

	public int getIdReservation() { return idReservation; }
	public void setIdReservation(int idReservation) { this.idReservation = idReservation; }
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	public Screening getScreening() { return screening; }
	public void setScreening(Screening screening) { this.screening = screening; }
	public int getTicketQuantity() { return ticketQuantity; }
	public void setTicketQuantity(int ticketQuantity) { this.ticketQuantity = ticketQuantity; }
	public boolean getStatus() { return status; }
	public void setStatus(boolean status) { this.status = status; }
}
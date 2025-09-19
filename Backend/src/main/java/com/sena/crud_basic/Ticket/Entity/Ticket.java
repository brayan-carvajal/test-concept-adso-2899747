package com.sena.crud_basic.Ticket.Entity;

import jakarta.persistence.*;
import com.sena.crud_basic.Customer.Entity.Customer;
import com.sena.crud_basic.Screening.Entity.Screening;

@Entity(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTicket")
	private int idTicket;

	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idScreening", nullable = false)
	private Screening screening;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name="status", nullable = false, columnDefinition = "boolean default true ")
	private boolean status;

	public Ticket() {}

	public Ticket(int idTicket, Customer customer, Screening screening, double price, boolean status) {
		this.idTicket = idTicket;
		this.customer = customer;
		this.screening = screening;
		this.price = price;
		this.status = status;
	}

	public int getIdTicket() { return idTicket; }
	public void setIdTicket(int idTicket) { this.idTicket = idTicket; }
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	public Screening getScreening() { return screening; }
	public void setScreening(Screening screening) { this.screening = screening; }
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	public boolean getStatus() { return status; }
	public void setStatus(boolean status) { this.status = status; }
}
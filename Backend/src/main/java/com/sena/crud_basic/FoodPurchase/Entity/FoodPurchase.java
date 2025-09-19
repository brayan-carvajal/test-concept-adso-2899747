package com.sena.crud_basic.FoodPurchase.Entity;

import jakarta.persistence.*;
import com.sena.crud_basic.Customer.Entity.Customer;
import com.sena.crud_basic.Food.Entity.Food;

@Entity(name = "foodpurchase")
public class FoodPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPurchase")
    private int idPurchase;

    @ManyToOne
    @JoinColumn(name = "idCustomer", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idFood", nullable = false)
    private Food food;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
    private boolean status;

    public FoodPurchase() {}

    public FoodPurchase(int idPurchase, Customer customer, Food food, int quantity, boolean status) {
        this.idPurchase = idPurchase;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
        this.status = status;
    }

    public int getIdPurchase() { return idPurchase; }
    public void setIdPurchase(int idPurchase) { this.idPurchase = idPurchase; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public boolean getStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
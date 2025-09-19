package com.sena.crud_basic.FoodPurchase.DTO;

import com.sena.crud_basic.Customer.Entity.Customer;
import com.sena.crud_basic.Food.Entity.Food;

public class FoodPurchaseDTO {
    private int idPurchase;
    private Customer customer;
    private Food food;
    private int quantity;

    public FoodPurchaseDTO() {}

    public FoodPurchaseDTO(int idPurchase, Customer customer, Food food, int quantity) {
        this.idPurchase = idPurchase;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
    }

    public int getIdPurchase() { return idPurchase; }
    public void setIdPurchase(int idPurchase) { this.idPurchase = idPurchase; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
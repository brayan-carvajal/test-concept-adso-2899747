package com.sena.crud_basic.Customer.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.Customer.Entity.Customer;

public interface ICustomer extends JpaRepository<Customer, Integer> {
    @Query("SELECT u FROM customer u WHERE u.status != false")
    List<Customer> getActive();

    @Query("SELECT u FROM customer u WHERE u.status != false AND (u.name LIKE %?1% OR u.email LIKE %?1%)")
    List<Customer> searchByNameOrEmail(String filter);
}

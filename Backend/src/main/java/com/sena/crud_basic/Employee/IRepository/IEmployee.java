package com.sena.crud_basic.Employee.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.Employee.Entity.Employee;

public interface IEmployee extends JpaRepository<Employee, Integer> {
    @Query("SELECT u FROM employee u WHERE u.status != false")
    List<Employee> getActive();

    @Query("SELECT u FROM employee u WHERE u.status != false AND (u.name LIKE %?1% OR u.position LIKE %?1%)")
    List<Employee> searchByNameOrPosition(String filter);
}
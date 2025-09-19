package com.sena.crud_basic.EmployeeShift.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.EmployeeShift.Entity.EmployeeShift;

public interface IEmployeeShift extends JpaRepository<EmployeeShift, Integer> {
    @Query("SELECT u FROM employeeshift u WHERE u.status != false")
    List<EmployeeShift> getActive();

    @Query("SELECT fp FROM employeeshift fp WHERE fp.status = true")
    List<EmployeeShift> findActiveEmployee();
}
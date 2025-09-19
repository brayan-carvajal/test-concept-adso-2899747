package com.sena.crud_basic.EmployeeShift.DTO;

import java.time.LocalDate;
import com.sena.crud_basic.Employee.Entity.Employee;

public class EmployeeShiftDTO {
    private int idShift;
    private Employee employee;
    private LocalDate dateTime;

    public EmployeeShiftDTO() {}

    public EmployeeShiftDTO(int idShift, Employee employee, LocalDate dateTime) {
        this.idShift = idShift;
        this.employee = employee;
        this.dateTime = dateTime;
    }

    public int getIdShift() { return idShift; }
    public void setIdShift(int idShift) { this.idShift = idShift; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public LocalDate getDateTime() { return dateTime; }
    public void setDateTime(LocalDate dateTime) { this.dateTime = dateTime; }
}
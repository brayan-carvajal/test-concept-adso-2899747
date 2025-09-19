package com.sena.crud_basic.EmployeeShift.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.sena.crud_basic.Employee.Entity.Employee;

@Entity(name = "employeeshift")
public class EmployeeShift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idShift")
	private int idShift;

	@ManyToOne
	@JoinColumn(name = "idEmployee", nullable = false)
	private Employee employee;

	@Column(name = "dateTime", nullable = false)
	private LocalDate dateTime;

	@Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
	private boolean status;

	public EmployeeShift() {}

	public EmployeeShift(int idShift, Employee employee, LocalDate dateTime, boolean status) {
		this.idShift = idShift;
		this.employee = employee;
		this.dateTime = dateTime;
		this.status = status;
	}

	public int getIdShift() { return idShift; }
	public void setIdShift(int idShift) { this.idShift = idShift; }
	public Employee getEmployee() { return employee; }
	public void setEmployee(Employee employee) { this.employee = employee; }
	public LocalDate getDateTime() { return dateTime; }
	public void setDateTime(LocalDate dateTime) { this.dateTime = dateTime; }
	public boolean getStatus() { return status; }
	public void setStatus(boolean status) { this.status = status; }
}
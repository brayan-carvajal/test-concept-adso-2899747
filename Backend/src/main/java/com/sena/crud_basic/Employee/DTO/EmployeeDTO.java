package com.sena.crud_basic.Employee.DTO;

public class EmployeeDTO {
    private int idEmployee;
    private String name;
    private String position;

    public EmployeeDTO() {}

    public EmployeeDTO(int idEmployee, String name, String position) {
        this.idEmployee =  idEmployee;
        this.name = name;
        this.position = position;
    }

    public int getIdEmployee() { return idEmployee; }
    public void setIdEmployee(int idEmployee) { this.idEmployee = idEmployee; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
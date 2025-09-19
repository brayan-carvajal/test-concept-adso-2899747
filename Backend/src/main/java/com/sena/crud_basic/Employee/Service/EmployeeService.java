package com.sena.crud_basic.Employee.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.Employee.DTO.EmployeeDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Employee.Entity.Employee;
import com.sena.crud_basic.Employee.IRepository.IEmployee;

@Service
public class EmployeeService {
    @Autowired
    private IEmployee data;

    public ResponseDTO save(EmployeeDTO employeeDTO) {
        if (employeeDTO.getName().length() < 1 || employeeDTO.getName().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de nombre deben estar entre 1 y 50");
            return respuesta;
        }
        if (employeeDTO.getPosition().length() < 1 || employeeDTO.getPosition().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de posición deben estar entre 1 y 50");
            return respuesta;
        }
        Employee employeeRegister = convertToModel(employeeDTO);
        data.save(employeeRegister);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se guardó correctamente");
        return respuesta;
    }

    public List<Employee> findAll() {
        return data.getActive();
    }

    public List<Employee> searchByNameOrPosition(String filter) {
        return data.searchByNameOrPosition(filter);
    }

    public Optional<Employee> findById(int idEmployee) {
        return data.findById(idEmployee);
    }

    public ResponseDTO deleteEmployee(int idEmployee) {
        Optional<Employee> employee = findById(idEmployee);
        if (!employee.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El registro no existe");
            return respuesta;
        }
        employee.get().setStatus(false);
        data.save(employee.get());
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se eliminó correctamente");
        return respuesta;
    }

    public ResponseDTO updateEmployee(int idEmployee, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = findById(idEmployee);
        if (!existingEmployee.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "Empleado no encontrado");
            return respuesta;
        }
        if (employeeDTO.getName().length() < 1 || employeeDTO.getName().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de nombre deben estar entre 1 y 50");
            return respuesta;
        }
        if (employeeDTO.getPosition().length() < 1 || employeeDTO.getPosition().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de posición deben estar entre 1 y 50");
            return respuesta;
        }
        Employee updatedEmployee = existingEmployee.get();
        updatedEmployee.setName(employeeDTO.getName());
        updatedEmployee.setPosition(employeeDTO.getPosition());
        data.save(updatedEmployee);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Empleado actualizado correctamente");
        return respuesta;
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getIdEmployee(),
                employee.getName(),
                employee.getPosition());
    }

    public Employee convertToModel(EmployeeDTO employeeDTO) {
        return new Employee(
                0,
                employeeDTO.getName(),
                employeeDTO.getPosition(),
                true);
    }
}
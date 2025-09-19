package com.sena.crud_basic.EmployeeShift.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.EmployeeShift.DTO.EmployeeShiftDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.EmployeeShift.Entity.EmployeeShift;
import com.sena.crud_basic.EmployeeShift.IRepository.IEmployeeShift;

@Service
public class EmployeeShiftService {
    @Autowired
    private IEmployeeShift data;

    public ResponseDTO save(EmployeeShiftDTO employeeshiftDTO) {
        if (employeeshiftDTO.getEmployee() == null) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El empleado es obligatorio");
            return respuesta;
        }
        if (employeeshiftDTO.getDateTime() == null) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La fecha es obligatoria");
            return respuesta;
        }
        EmployeeShift employeeshiftRegister = convertToModel(employeeshiftDTO);
        data.save(employeeshiftRegister);
    ResponseDTO respuesta = new ResponseDTO(
        HttpStatus.OK.toString(),
        "Se guardó correctamente");
    return respuesta;
    }

    public List<EmployeeShift> findAll() {
        return data.getActive();
    }

    public List<EmployeeShift> searchByEmployee(String filter) {
        List<EmployeeShift> activeEmployee = data.findActiveEmployee();
        String filterLowerCase = filter.toLowerCase();
        return activeEmployee.stream()
                .filter(fp -> fp.getEmployee().getName().toLowerCase().contains(filterLowerCase))
                .collect(Collectors.toList());
    }

    public Optional<EmployeeShift> findById(int idEmployeeshift) {
        return data.findById(idEmployeeshift);
    }

    public ResponseDTO deleteEmployeeshift(int idEmployeeshift) {
        Optional<EmployeeShift> employeeshift = findById(idEmployeeshift);
        if (!employeeshift.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El turno no existe");
            return respuesta;
        }
        employeeshift.get().setStatus(false);
        data.save(employeeshift.get());
    ResponseDTO respuesta = new ResponseDTO(
        HttpStatus.OK.toString(),
        "Se eliminó correctamente");
    return respuesta;
    }

    public ResponseDTO updateEmployeeshift(int idEmployeeshift, EmployeeShiftDTO employeeshiftDTO) {
        Optional<EmployeeShift> employeeshiftOptional = data.findById(idEmployeeshift);
        if (!employeeshiftOptional.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El turno con ID " + idEmployeeshift + " no existe");
            return respuesta;
        }
        EmployeeShift existingShift = employeeshiftOptional.get();
        if (employeeshiftDTO.getEmployee() == null) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El empleado es obligatorio");
            return respuesta;
        }
        if (employeeshiftDTO.getDateTime() == null) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La fecha es obligatoria");
            return respuesta;
        }
        existingShift.setEmployee(employeeshiftDTO.getEmployee());
        existingShift.setDateTime(employeeshiftDTO.getDateTime());
        data.save(existingShift);
    ResponseDTO respuesta = new ResponseDTO(
        HttpStatus.OK.toString(),
        "Turno actualizado correctamente");
    return respuesta;
    }

    public EmployeeShiftDTO convertToDTO(EmployeeShift employeeshift) {
        return new EmployeeShiftDTO(
                employeeshift.getIdShift(),
                employeeshift.getEmployee(),
                employeeshift.getDateTime());
    }

    public EmployeeShift convertToModel(EmployeeShiftDTO employeeshiftDTO) {
        return new EmployeeShift(
                0,
                employeeshiftDTO.getEmployee(),
                employeeshiftDTO.getDateTime(),
                true);
    }
}
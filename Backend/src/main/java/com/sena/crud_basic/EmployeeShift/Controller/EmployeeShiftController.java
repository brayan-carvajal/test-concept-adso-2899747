package com.sena.crud_basic.EmployeeShift.Controller;

import com.sena.crud_basic.EmployeeShift.DTO.EmployeeShiftDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.EmployeeShift.Service.EmployeeShiftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employeeshift")
public class EmployeeShiftController {
    @Autowired
    private EmployeeShiftService employeeShiftService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerEmployeeShift(@RequestBody EmployeeShiftDTO employeeshift) {
    ResponseDTO respuesta = employeeShiftService.save(employeeshift);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllEmployeeShifts() {
        var listaEmployeeShifts = employeeShiftService.findAll();
        return new ResponseEntity<>(listaEmployeeShifts, HttpStatus.OK);
    }

    @GetMapping("/{idEmployeeShift}")
    public ResponseEntity<Object> getOneEmployeeShift(@PathVariable int idEmployeeShift) {
        var employeeshift = employeeShiftService.findById(idEmployeeShift);
        if (!employeeshift.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeshift, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchEmployeeShifts(@PathVariable String filter) {
        var employeeShiftList = employeeShiftService.searchByEmployee(filter);
        return new ResponseEntity<>(employeeShiftList, HttpStatus.OK);
    }

    @DeleteMapping("/{idEmployeeShift}")
    public ResponseEntity<Object> deleteEmployeeShift(@PathVariable int idEmployeeShift) {
        var message = employeeShiftService.deleteEmployeeshift(idEmployeeShift);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idEmployeeShift}")
    public ResponseEntity<Object> updateEmployeeShift(@PathVariable int idEmployeeShift,
            @RequestBody EmployeeShiftDTO employeeshift) {
    ResponseDTO respuesta = employeeShiftService.updateEmployeeshift(idEmployeeShift, employeeshift);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
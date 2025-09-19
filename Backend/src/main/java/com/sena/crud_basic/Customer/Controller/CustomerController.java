package com.sena.crud_basic.Customer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.Customer.DTO.CustomerDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Customer.Service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerCustomer(@RequestBody CustomerDTO customer) {
    ResponseDTO respuesta = customerService.save(customer);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllCustomer() {
        var listaCustomer = customerService.findAll();
        return new ResponseEntity<>(listaCustomer, HttpStatus.OK);
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<Object> getOneCustomer(@PathVariable int idCustomer) {
        var customer = customerService.findById(idCustomer);
        if (!customer.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchMovies(@PathVariable String filter) {
        var customerList = customerService.searchByNameOrEmail(filter);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int idCustomer) {
        var message = customerService.deleteCustomer(idCustomer);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idCustomer}")
    public ResponseEntity<Object> updateCustomer(@PathVariable int idCustomer, @RequestBody CustomerDTO customer) {
    ResponseDTO respuesta = customerService.updateCustomer(idCustomer, customer);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}

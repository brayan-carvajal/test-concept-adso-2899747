package com.sena.crud_basic.Customer.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.Customer.DTO.CustomerDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Customer.Entity.Customer;
import com.sena.crud_basic.Customer.IRepository.ICustomer;

@Service
public class CustomerService {
    @Autowired
    private ICustomer data;

    public ResponseDTO save(CustomerDTO customerDTO) {
        if (customerDTO.getName().length() < 1 || customerDTO.getName().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de nombre deben estar entre 1 y 50");
            return respuesta;
        }
        if (customerDTO.getEmail().length() < 1 || customerDTO.getEmail().length() > 100) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de email deben estar entre 1 y 100");
            return respuesta;
        }
        if (customerDTO.getPassword().length() < 1 || customerDTO.getPassword().length() > 20) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de password deben estar entre 1 y 20");
            return respuesta;
        }
        Customer customerRegister = convertToModel(customerDTO);
        data.save(customerRegister);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se guardó correctamente");
        return respuesta;
    }

    public List<Customer> findAll() {
        return data.getActive();
    }

    public List<Customer> searchByNameOrEmail(String filter) {
        return data.searchByNameOrEmail(filter);
    }

    public Optional<Customer> findById(int idCustomer) {
        return data.findById(idCustomer);
    }

    public ResponseDTO deleteCustomer(int idCustomer) {
        Optional<Customer> customer = findById(idCustomer);
        if (!customer.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El registro no existe");
            return respuesta;
        }
        customer.get().setStatus(false);
        data.save(customer.get());
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se eliminó correctamente");
        return respuesta;
    }

    public ResponseDTO updateCustomer(int idCustomer, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = data.findById(idCustomer);
        if (!customerOptional.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El cliente con ID " + idCustomer + " no existe");
            return respuesta;
        }
        Customer existingCustomer = customerOptional.get();
        if (customerDTO.getName().length() < 1 || customerDTO.getName().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de nombre deben estar entre 1 y 50");
            return respuesta;
        }
        if (customerDTO.getEmail().length() < 1 || customerDTO.getEmail().length() > 100) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de email deben estar entre 1 y 100");
            return respuesta;
        }
        if (customerDTO.getPassword().length() < 1 || customerDTO.getPassword().length() > 20) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres de password deben estar entre 1 y 20");
            return respuesta;
        }
        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPassword(customerDTO.getPassword());
        data.save(existingCustomer);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Cliente actualizado correctamente");
        return respuesta;
    }

    public CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getIdCustomer(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword());
    }

    public Customer convertToModel(CustomerDTO customerDTO) {
        return new Customer(
                0,
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getPassword(),
                true);
    }
}

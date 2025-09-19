package com.sena.crud_basic.Ticket.Controller;

import com.sena.crud_basic.Ticket.DTO.TicketDTO;
import com.sena.crud_basic.Ticket.Service.TicketService;
import com.sena.crud_basic.Common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerTicket(@RequestBody TicketDTO ticket) {
        ResponseDTO respuesta = ticketService.save(ticket);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllTickets() {
        var listaTickets = ticketService.findAll();
        return new ResponseEntity<>(listaTickets, HttpStatus.OK);
    }

    @GetMapping("/{idTicket}")
    public ResponseEntity<Object> getOneTicket(@PathVariable int idTicket) {
        var ticket = ticketService.findById(idTicket);
        if (!ticket.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchTickets(@PathVariable String filter) {
        var ticketList = ticketService.searchByCustomerOrTitleOrRoomNumberOrPrice(filter);
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @DeleteMapping("/{idTicket}")
    public ResponseEntity<Object> deleteTicket(@PathVariable int idTicket) {
        var message = ticketService.deleteTicket(idTicket);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idTicket}")
    public ResponseEntity<Object> updateTicket(@PathVariable int idTicket, @RequestBody TicketDTO ticket) {
        ResponseDTO respuesta = ticketService.updateTicket(idTicket, ticket);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}

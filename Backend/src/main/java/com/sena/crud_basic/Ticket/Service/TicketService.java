package com.sena.crud_basic.Ticket.Service;

import com.sena.crud_basic.Ticket.DTO.TicketDTO;
import com.sena.crud_basic.Ticket.Entity.Ticket;
import com.sena.crud_basic.Ticket.Repository.ITicket;
import com.sena.crud_basic.Common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private ITicket data;

    public ResponseDTO save(TicketDTO ticketDTO) {
        if (ticketDTO.getPrice() <= 0) {
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "El precio del boleto debe ser mayor a 0.");
        }
        Ticket ticketRegister = convertToModel(ticketDTO);
        data.save(ticketRegister);
    return new ResponseDTO(HttpStatus.OK.toString(), "Boleto registrado correctamente.");
    }

    public List<Ticket> findAll() {
        return data.getActive();
    }

    public List<Ticket> searchByCustomerOrTitleOrRoomNumberOrPrice(String filter) {
        List<Ticket> activeTickets = data.findActiveTickets();
        String filterLowerCase = filter.toLowerCase();
        return activeTickets.stream()
                .filter(t -> {
                    boolean matchesCustomerName = t.getCustomer().getName().toLowerCase().contains(filterLowerCase);
                    boolean matchesMovieTitle = t.getScreening().getMovie().getTitle().toLowerCase().contains(filterLowerCase);
                    boolean matchesRoomNumber = String.valueOf(t.getScreening().getRoom().getRoomNumber()).contains(filter);
                    boolean matchesPrice = String.valueOf(t.getPrice()).contains(filter);
                    return matchesCustomerName || matchesMovieTitle || matchesRoomNumber || matchesPrice;
                })
                .collect(Collectors.toList());
    }

    public Optional<Ticket> findById(int idTicket) {
        return data.findById(idTicket);
    }

    public ResponseDTO deleteTicket(int idTicket) {
        Optional<Ticket> ticket = findById(idTicket);
        if (!ticket.isPresent()) {
            return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "El boleto no existe.");
        }
        ticket.get().setStatus(false);
        data.save(ticket.get());
    return new ResponseDTO(HttpStatus.OK.toString(), "Boleto eliminado correctamente.");
    }

    public ResponseDTO updateTicket(int idTicket, TicketDTO ticketDTO) {
        Optional<Ticket> ticketOptional = data.findById(idTicket);
        if (!ticketOptional.isPresent()) {
            return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "El boleto con ID " + idTicket + " no existe.");
        }
        if (ticketDTO.getPrice() <= 0) {
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "El precio del boleto debe ser mayor a 0.");
        }
        Ticket existingTicket = ticketOptional.get();
        existingTicket.setCustomer(ticketDTO.getCustomer());
        existingTicket.setScreening(ticketDTO.getScreening());
        existingTicket.setPrice(ticketDTO.getPrice());
        data.save(existingTicket);
    return new ResponseDTO(HttpStatus.OK.toString(), "Boleto actualizado correctamente.");
    }

    public TicketDTO convertToDTO(Ticket ticket) {
        return new TicketDTO(ticket.getIdTicket(), ticket.getCustomer(), ticket.getScreening(), ticket.getPrice());
    }

    public Ticket convertToModel(TicketDTO ticketDTO) {
        return new Ticket(0, ticketDTO.getCustomer(), ticketDTO.getScreening(), ticketDTO.getPrice(), true);
    }
}

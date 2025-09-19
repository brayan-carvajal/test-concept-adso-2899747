package com.sena.crud_basic.Reservation.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.Reservation.DTO.ReservationDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Reservation.Entity.Reservation;
import com.sena.crud_basic.Reservation.IRepository.IReservation;

@Service
public class ReservationService {
    @Autowired
    private IReservation data;

    public ResponseDTO save(ReservationDTO reservationDTO) {
        Reservation reservationRegister = convertToModel(reservationDTO);
        data.save(reservationRegister);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Reservación registrada correctamente");
        return respuesta;
    }

    public List<Reservation> findAll() {
        return data.getActive();
    }

    public List<Reservation> searchByCustomerOrTitleOrRoomNumberOrTicket(String filter) {
        List<Reservation> activeReservations = data.findActiveReservations();
        String filterLowerCase = filter.toLowerCase();
        return activeReservations.stream()
                .filter(r -> {
                    boolean matchesCustomerName = r.getCustomer().getName().toLowerCase().contains(filterLowerCase);
                    boolean matchesMovieTitle = r.getScreening().getMovie().getTitle().toLowerCase().contains(filterLowerCase);
                    boolean matchesRoomNumber = String.valueOf(r.getScreening().getRoom().getRoomNumber()).contains(filter);
                    boolean matchesTicketCount = String.valueOf(r.getTicketQuantity()).contains(filter);
                    return matchesCustomerName || matchesMovieTitle || matchesRoomNumber || matchesTicketCount;
                })
                .collect(Collectors.toList());
    }

    public Optional<Reservation> findById(int idReservation) {
        return data.findById(idReservation);
    }

    public ResponseDTO deleteReservation(int idReservation) {
        Optional<Reservation> reservation = findById(idReservation);
        if (!reservation.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La reservación no existe");
            return respuesta;
        }
        reservation.get().setStatus(false);
        data.save(reservation.get());
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Reservación eliminada correctamente");
        return respuesta;
    }

    public ResponseDTO updateReservation(int idReservation, ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOptional = data.findById(idReservation);
        if (!reservationOptional.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La reservación con ID " + idReservation + " no existe");
            return respuesta;
        }
        Reservation existingReservation = reservationOptional.get();
        existingReservation.setCustomer(reservationDTO.getCustomer());
        existingReservation.setScreening(reservationDTO.getScreening());
        existingReservation.setTicketQuantity(reservationDTO.getTicketQuantity());
        data.save(existingReservation);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Reservación actualizada correctamente");
        return respuesta;
    }

    public ReservationDTO convertToDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getIdReservation(),
                reservation.getCustomer(),
                reservation.getScreening(),
                reservation.getTicketQuantity());
    }

    public Reservation convertToModel(ReservationDTO reservationDTO) {
        return new Reservation(
                0,
                reservationDTO.getCustomer(),
                reservationDTO.getScreening(),
                reservationDTO.getTicketQuantity(),
                true);
    }
}
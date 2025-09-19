package com.sena.crud_basic.Reservation.Controller;

import com.sena.crud_basic.Reservation.DTO.ReservationDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Reservation.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerReservation(@RequestBody ReservationDTO reservation) {
    ResponseDTO respuesta = reservationService.save(reservation);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllReservations() {
        var listaReservations = reservationService.findAll();
        return new ResponseEntity<>(listaReservations, HttpStatus.OK);
    }

    @GetMapping("/{idReservation}")
    public ResponseEntity<Object> getOneReservation(@PathVariable int idReservation) {
        var reservation = reservationService.findById(idReservation);
        if (!reservation.isPresent()) {
            return new ResponseEntity<>("Reservación no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchReservations(@PathVariable String filter) {
        var reservationList = reservationService.searchByCustomerOrTitleOrRoomNumberOrTicket(filter);
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

    @DeleteMapping("/{idReservation}")
    public ResponseEntity<Object> deleteReservation(@PathVariable int idReservation) {
    ResponseDTO mensaje = reservationService.deleteReservation(idReservation);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PutMapping("/{idReservation}")
    public ResponseEntity<Object> updateReservation(@PathVariable int idReservation,
            @RequestBody ReservationDTO reservation) {
    ResponseDTO respuesta = reservationService.updateReservation(idReservation, reservation);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
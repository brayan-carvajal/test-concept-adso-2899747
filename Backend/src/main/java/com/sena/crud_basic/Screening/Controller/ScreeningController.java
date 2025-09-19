package com.sena.crud_basic.Screening.Controller;

import com.sena.crud_basic.Screening.DTO.ScreeningDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Screening.Service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/screening")
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerScreening(@RequestBody ScreeningDTO screening) {
        ResponseDTO respuesta = screeningService.save(screening);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllScreenings() {
        var listaScreenings = screeningService.findAll();
        return new ResponseEntity<>(listaScreenings, HttpStatus.OK);
    }

    @GetMapping("/{idScreening}")
    public ResponseEntity<Object> getOneScreening(@PathVariable int idScreening) {
        var screening = screeningService.findById(idScreening);
        if (!screening.isPresent())
            return new ResponseEntity<>("Proyección no encontrada", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(screening, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchScreenings(@PathVariable String filter) {
        var screeningList = screeningService.searchByTitleOrRoomNumber(filter);
        return new ResponseEntity<>(screeningList, HttpStatus.OK);
    }

    @DeleteMapping("/{idScreening}")
    public ResponseEntity<Object> deleteScreening(@PathVariable int idScreening) {
        var message = screeningService.deleteScreening(idScreening);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idScreening}")
    public ResponseEntity<Object> updateScreening(@PathVariable int idScreening, @RequestBody ScreeningDTO screening) {
        ResponseDTO respuesta = screeningService.updateScreening(idScreening, screening);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
package com.sena.crud_basic.Room.Controller;

import com.sena.crud_basic.Room.DTO.RoomDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Room.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerRoom(@RequestBody RoomDTO room) {
    ResponseDTO respuesta = roomService.save(room);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllRooms() {
        var listaRooms = roomService.findAll();
        return new ResponseEntity<>(listaRooms, HttpStatus.OK);
    }

    @GetMapping("/{idRoom}")
    public ResponseEntity<Object> getOneRoom(@PathVariable int idRoom) {
        var room = roomService.findById(idRoom);
        if (!room.isPresent()) {
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "Sala no encontrada"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchRoom(@PathVariable String filter) {
        var roomNumberList = roomService.searchByRoomNumberOrCapacity(filter);
        return new ResponseEntity<>(roomNumberList, HttpStatus.OK);
    }

    @DeleteMapping("/{idRoom}")
    public ResponseEntity<Object> deleteRoom(@PathVariable int idRoom) {
    ResponseDTO respuesta = roomService.deleteRoom(idRoom);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{idRoom}")
    public ResponseEntity<Object> updateRoom(@PathVariable int idRoom, @RequestBody RoomDTO room) {
    ResponseDTO respuesta = roomService.updateRoom(idRoom, room);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
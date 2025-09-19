package com.sena.crud_basic.Room.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.Room.DTO.RoomDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Room.Entity.Room;
import com.sena.crud_basic.Room.IRepository.IRoom;

@Service
public class RoomService {
    @Autowired
    private IRoom data;

    public ResponseDTO save(RoomDTO roomDTO) {
        if (roomDTO.getRoomNumber() <= 0) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El número de la sala debe ser mayor a 0");
            return respuesta;
        }
        if (roomDTO.getCapacity() < 1 || roomDTO.getCapacity() > 500) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La capacidad debe estar entre 1 y 500");
            return respuesta;
        }
        Room roomRegister = convertToModel(roomDTO);
        data.save(roomRegister);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Sala registrada correctamente");
        return respuesta;
    }

    public List<Room> findAll() {
        return data.getActive();
    }

    public List<Room> searchByRoomNumberOrCapacity(String filter) {
        return data.searchByRoomNumberOrCapacity(filter);
    }

    public Optional<Room> findById(int id) {
        return data.findById(id);
    }

    public ResponseDTO deleteRoom(int idRoom) {
        Optional<Room> room = findById(idRoom);
        if (!room.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "Sala no encontrada");
            return respuesta;
        }
        room.get().setStatus(false);
        data.save(room.get());
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Sala eliminada correctamente");
        return respuesta;
    }

    public ResponseDTO updateRoom(int id, RoomDTO roomDTO) {
        Optional<Room> roomOptional = data.findById(id);
        if (!roomOptional.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La sala con ID " + id + " no existe");
            return respuesta;
        }
        Room existingRoom = roomOptional.get();
        if (roomDTO.getRoomNumber() <= 0) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El número de la sala debe ser mayor a 0");
            return respuesta;
        }
        if (roomDTO.getCapacity() < 1 || roomDTO.getCapacity() > 500) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La capacidad debe estar entre 1 y 500");
            return respuesta;
        }
        existingRoom.setRoomNumber(roomDTO.getRoomNumber());
        existingRoom.setCapacity(roomDTO.getCapacity());
        data.save(existingRoom);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Sala actualizada correctamente");
        return respuesta;
    }

    public RoomDTO convertToDTO(Room room) {
        return new RoomDTO(
                room.getIdRoom(),
                room.getRoomNumber(),
                room.getCapacity());
    }

    public Room convertToModel(RoomDTO roomDTO) {
        return new Room(
                0,
                roomDTO.getRoomNumber(),
                roomDTO.getCapacity(),
                true);
    }
}
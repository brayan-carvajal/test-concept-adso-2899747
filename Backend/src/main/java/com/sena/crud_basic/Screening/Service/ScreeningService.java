package com.sena.crud_basic.Screening.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.Screening.DTO.ScreeningDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Screening.Entity.Screening;
import com.sena.crud_basic.Screening.IRepository.IScreening;

@Service
public class ScreeningService {
    @Autowired
    private IScreening data;

    public ResponseDTO save(ScreeningDTO screeningDTO) {
        if (screeningDTO.getMovie() == null || screeningDTO.getRoom() == null || screeningDTO.getDateTime() == null) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Todos los campos son obligatorios");
            return respuesta;
        }
        Screening screeningRegister = convertToModel(screeningDTO);
        data.save(screeningRegister);
        ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Función guardada correctamente");
        return respuesta;
    }

    public List<Screening> findAll() {
        return data.getActive();
    }

    public List<Screening> searchByTitleOrRoomNumber(String filter) {
        List<Screening> activeScreenings = data.findActiveScreenings();
        String filterLowerCase = filter.toLowerCase();
        return activeScreenings.stream()
                .filter(s -> {
                    boolean matchesTitle = s.getMovie().getTitle().toLowerCase().contains(filterLowerCase);
                    boolean matchesRoomNumber = String.valueOf(s.getRoom().getRoomNumber()).contains(filter);
                    return matchesTitle || matchesRoomNumber;
                })
                .collect(Collectors.toList());
    }

    public Optional<Screening> findById(int idScreening) {
        return data.findById(idScreening);
    }

    public ResponseDTO deleteScreening(int idScreening) {
        Optional<Screening> screening = findById(idScreening);
        if (!screening.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La proyección no existe");
            return respuesta;
        }
        screening.get().setStatus(false);
        data.save(screening.get());
        ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Proyección eliminada correctamente");
        return respuesta;
    }

    public ResponseDTO updateScreening(int idScreening, ScreeningDTO screeningDTO) {
        Optional<Screening> screeningOptional = data.findById(idScreening);
        if (!screeningOptional.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La proyección con ID " + idScreening + " no existe");
            return respuesta;
        }
        Screening existingScreening = screeningOptional.get();
        if (screeningDTO.getMovie() == null || screeningDTO.getRoom() == null || screeningDTO.getDateTime() == null) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Todos los campos son obligatorios");
            return respuesta;
        }
        existingScreening.setMovie(screeningDTO.getMovie());
        existingScreening.setRoom(screeningDTO.getRoom());
        existingScreening.setDateTime(screeningDTO.getDateTime());
        data.save(existingScreening);
        ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Proyección actualizada correctamente");
        return respuesta;
    }

    public ScreeningDTO convertToDTO(Screening screening) {
        return new ScreeningDTO(
                screening.getIdScreening(),
                screening.getMovie(),
                screening.getRoom(),
                screening.getDateTime());
    }

    public Screening convertToModel(ScreeningDTO screeningDTO) {
        return new Screening(
                0,
                screeningDTO.getMovie(),
                screeningDTO.getRoom(),
                screeningDTO.getDateTime(),
                true);
    }
}
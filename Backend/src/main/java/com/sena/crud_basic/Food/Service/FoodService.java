package com.sena.crud_basic.Food.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.Food.DTO.FoodDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Food.Entity.Food;
import com.sena.crud_basic.Food.IRepository.IFood;

@Service
public class FoodService {
    @Autowired
    private IFood data;

    public ResponseDTO save(FoodDTO foodDTO) {
        if (foodDTO.getName().length() < 1 || foodDTO.getName().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres del nombre deben estar entre 1 y 50");
            return respuesta;
        }
        if (foodDTO.getPrice() <= 0) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El precio debe ser mayor que 0");
            return respuesta;
        }
        Food foodRegister = convertToModel(foodDTO);
        data.save(foodRegister);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se guardó correctamente");
        return respuesta;
    }

    public List<Food> findAll() {
        return data.getActive();
    }

    public List<Food> searchByNameOrPrice(String filter) {
        return data.searchByNameOrPrice(filter);
    }

    public Optional<Food> findById(int idFood) {
        return data.findById(idFood);
    }

    public ResponseDTO deleteFood(int idFood) {
        Optional<Food> food = findById(idFood);
        if (!food.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El registro no existe");
            return respuesta;
        }
        food.get().setStatus(false);
        data.save(food.get());
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se eliminó correctamente");
        return respuesta;
    }

    public ResponseDTO updateFood(int idFood, FoodDTO foodDTO) {
        Optional<Food> foodOptional = data.findById(idFood);
        if (!foodOptional.isPresent()) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La comida con ID " + idFood + " no existe");
            return respuesta;
        }
        Food existingFood = foodOptional.get();
        if (foodDTO.getName().length() < 1 || foodDTO.getName().length() > 50) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "Los caracteres del nombre deben estar entre 1 y 50");
            return respuesta;
        }
        if (foodDTO.getPrice() <= 0) {
            ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "El precio debe ser mayor que 0");
            return respuesta;
        }
        existingFood.setName(foodDTO.getName());
        existingFood.setPrice(foodDTO.getPrice());
        existingFood.setImgUrl(foodDTO.getImgUrl());
        data.save(existingFood);
    ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Comida actualizada correctamente");
        return respuesta;
    }

    public FoodDTO convertToDTO(Food food) {
        return new FoodDTO(
                food.getIdFood(),
                food.getName(),
                food.getPrice(),
                food.getImgUrl());
    }

    public Food convertToModel(FoodDTO foodDTO) {
        return new Food(
                0,
                foodDTO.getName(),
                foodDTO.getPrice(),
                foodDTO.getImgUrl(),
                true);
    }
}
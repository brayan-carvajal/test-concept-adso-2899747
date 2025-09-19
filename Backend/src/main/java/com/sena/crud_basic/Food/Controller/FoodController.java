package com.sena.crud_basic.Food.Controller;

import com.sena.crud_basic.Food.DTO.FoodDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.Food.Service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerFood(@RequestBody FoodDTO food) {
    ResponseDTO respuesta = foodService.save(food);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllFood() {
        var listaFood = foodService.findAll();
        return new ResponseEntity<>(listaFood, HttpStatus.OK);
    }

    @GetMapping("/{idFood}")
    public ResponseEntity<Object> getOneFood(@PathVariable int idFood) {
        var food = foodService.findById(idFood);
        if (!food.isPresent())
            return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchFood(@PathVariable String filter) {
        var foodList = foodService.searchByNameOrPrice(filter);
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @DeleteMapping("/{idFood}")
    public ResponseEntity<Object> deleteFood(@PathVariable int idFood) {
        var message = foodService.deleteFood(idFood);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idFood}")
    public ResponseEntity<Object> updateFood(@PathVariable int idFood, @RequestBody FoodDTO food) {
    ResponseDTO respuesta = foodService.updateFood(idFood, food);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
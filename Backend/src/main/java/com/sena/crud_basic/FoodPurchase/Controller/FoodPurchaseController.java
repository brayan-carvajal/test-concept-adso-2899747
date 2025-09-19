package com.sena.crud_basic.FoodPurchase.Controller;

import com.sena.crud_basic.FoodPurchase.DTO.FoodPurchaseDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.FoodPurchase.Service.FoodPurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/foodpurchase")
public class FoodPurchaseController {
    @Autowired
    private FoodPurchaseService foodPurchaseService;

    @PostMapping("/post")
    public ResponseEntity<Object> registerFoodPurchase(@RequestBody FoodPurchaseDTO foodpurchase) {
    ResponseDTO respuesta = foodPurchaseService.save(foodpurchase);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllFoodPurchases() {
        var listaFoodPurchases = foodPurchaseService.findAll();
        return new ResponseEntity<>(listaFoodPurchases, HttpStatus.OK);
    }

    @GetMapping("/{idFoodPurchase}")
    public ResponseEntity<Object> getOneFoodPurchase(@PathVariable int idFoodPurchase) {
        var foodpurchase = foodPurchaseService.findById(idFoodPurchase);
        if (!foodpurchase.isPresent())
            return new ResponseEntity<>("Compra de comida no encontrada", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(foodpurchase, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> searchFoodPurchases(@PathVariable String filter) {
        var foodPurchaseList = foodPurchaseService.searchByCustomerOrFoodOrQuantity(filter);
        return new ResponseEntity<>(foodPurchaseList, HttpStatus.OK);
    }

    @DeleteMapping("/{idFoodPurchase}")
    public ResponseEntity<Object> deleteFoodPurchase(@PathVariable int idFoodPurchase) {
        var message = foodPurchaseService.deleteFoodPurchase(idFoodPurchase);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idFoodPurchase}")
    public ResponseEntity<Object> updateFoodPurchase(@PathVariable int idFoodPurchase,
            @RequestBody FoodPurchaseDTO foodpurchase) {
    ResponseDTO respuesta = foodPurchaseService.updateFoodPurchase(idFoodPurchase, foodpurchase);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
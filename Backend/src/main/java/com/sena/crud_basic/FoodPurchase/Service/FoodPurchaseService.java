package com.sena.crud_basic.FoodPurchase.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.FoodPurchase.DTO.FoodPurchaseDTO;
import com.sena.crud_basic.Common.ResponseDTO;
import com.sena.crud_basic.FoodPurchase.Entity.FoodPurchase;
import com.sena.crud_basic.FoodPurchase.IRepository.IFoodPurchase;

@Service
public class FoodPurchaseService {
    @Autowired
    private IFoodPurchase data;

    public ResponseDTO save(FoodPurchaseDTO foodpurchaseDTO) {
        if (foodpurchaseDTO.getQuantity() <= 0) {
                ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La cantidad debe ser mayor a 0");
            return respuesta;
        }
        FoodPurchase foodpurchaseRegister = convertToModel(foodpurchaseDTO);
        data.save(foodpurchaseRegister);
            ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se guardó correctamente");
        return respuesta;
    }

    public List<FoodPurchase> findAll() {
        return data.getActive();
    }

    public List<FoodPurchase> searchByCustomerOrFoodOrQuantity(String filter) {
        List<FoodPurchase> activeFoodPurchases = data.findActiveFoodPurchases();
        String filterLowerCase = filter.toLowerCase();
        return activeFoodPurchases.stream()
                .filter(fp -> {
                    boolean matchesCustomerName = fp.getCustomer().getName().toLowerCase().contains(filterLowerCase);
                    boolean matchesFoodName = fp.getFood().getName().toLowerCase().contains(filterLowerCase);
                    boolean matchesQuantity = String.valueOf(fp.getQuantity()).contains(filter);
                    return matchesCustomerName || matchesFoodName || matchesQuantity;
                })
                .collect(Collectors.toList());
    }

    public Optional<FoodPurchase> findById(int idFoodPurchase) {
        return data.findById(idFoodPurchase);
    }

    public ResponseDTO deleteFoodPurchase(int idFoodPurchase) {
        Optional<FoodPurchase> foodpurchase = findById(idFoodPurchase);
        if (!foodpurchase.isPresent()) {
                ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El registro no existe");
            return respuesta;
        }
        foodpurchase.get().setStatus(false);
        data.save(foodpurchase.get());
            ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Se eliminó correctamente");
        return respuesta;
    }

    public ResponseDTO updateFoodPurchase(int idFoodPurchase, FoodPurchaseDTO foodpurchaseDTO) {
        Optional<FoodPurchase> foodpurchaseOptional = data.findById(idFoodPurchase);
        if (!foodpurchaseOptional.isPresent()) {
                ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "La compra con ID " + idFoodPurchase + " no existe");
            return respuesta;
        }
        if (foodpurchaseDTO.getQuantity() <= 0) {
                ResponseDTO respuesta = new ResponseDTO(
                    HttpStatus.BAD_REQUEST.toString(),
                    "La cantidad debe ser mayor a 0");
            return respuesta;
        }
        FoodPurchase existingFoodPurchase = foodpurchaseOptional.get();
        existingFoodPurchase.setCustomer(foodpurchaseDTO.getCustomer());
        existingFoodPurchase.setFood(foodpurchaseDTO.getFood());
        existingFoodPurchase.setQuantity(foodpurchaseDTO.getQuantity());
        data.save(existingFoodPurchase);
            ResponseDTO respuesta = new ResponseDTO(
                HttpStatus.OK.toString(),
                "Compra actualizada correctamente");
        return respuesta;
    }

    public FoodPurchaseDTO convertToDTO(FoodPurchase foodpurchase) {
        return new FoodPurchaseDTO(
                foodpurchase.getIdPurchase(),
                foodpurchase.getCustomer(),
                foodpurchase.getFood(),
                foodpurchase.getQuantity());
    }

    public FoodPurchase convertToModel(FoodPurchaseDTO foodpurchaseDTO) {
        return new FoodPurchase(
                0,
                foodpurchaseDTO.getCustomer(),
                foodpurchaseDTO.getFood(),
                foodpurchaseDTO.getQuantity(),
                true);
    }
}
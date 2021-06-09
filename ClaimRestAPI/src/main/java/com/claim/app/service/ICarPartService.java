package com.claim.app.service;

import java.util.List;
import java.util.Optional;

import com.claim.app.entity.CarPart;

public interface ICarPartService {

    List<CarPart> getAllCarParts();
    List<CarPart> getAllCarPartsByIds(List<Long> ids);
    String getCarPartNameById(Long id);
    Optional<CarPart> getCarPartById(long carPartId);
    void addCarPart(CarPart carPart);
    void updateCarPart(CarPart carPart);
    void deleteCarPart(long carPart);
}

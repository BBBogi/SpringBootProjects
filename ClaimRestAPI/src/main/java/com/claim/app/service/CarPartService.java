package com.claim.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.app.entity.CarPart;
import com.claim.app.repository.CarPartRepository;

@Service
public class CarPartService implements ICarPartService {

	@Autowired
	private CarPartRepository carPartRepository;
	
	@Override
	public List<CarPart> getAllCarParts() {
		List<CarPart> list = new ArrayList<>();
		carPartRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Optional<CarPart> getCarPartById(long carPartId) {
		return carPartRepository.findById(carPartId);	
	}

	@Override
	public void addCarPart(CarPart carPart) {
		carPartRepository.save(carPart);
	}

	@Override
	public void updateCarPart(CarPart carPart) {
		carPartRepository.save(carPart);
	}

	@Override
	public void deleteCarPart(long carPartId) {
		Optional<CarPart> carPartToDelete = getCarPartById(carPartId);
		if(carPartToDelete.isPresent()) {
			carPartRepository.delete(carPartToDelete.get());
		}	
	}

	@Override
	public List<CarPart> getAllCarPartsByIds(List<Long> ids) {
		List<CarPart> list = new ArrayList<>();
		carPartRepository.findAllById(ids).forEach(e -> list.add(e));
		return list;

	}

	@Override
	public String getCarPartNameById(Long id) {
		String result = null;
		Optional<CarPart> carPart = carPartRepository.findById(id);
		if(carPart.isPresent()) {
			result = carPart.get().getName();
		}
		return result;
	}
}

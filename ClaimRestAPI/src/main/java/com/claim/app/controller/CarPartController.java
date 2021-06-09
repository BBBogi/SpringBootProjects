package com.claim.app.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.claim.app.entity.CarPart;
import com.claim.app.service.ICarPartService;

@RestController
public class CarPartController {

	@Autowired
	private ICarPartService carPartService;
	
	@GetMapping("carParts/{id}")
	public ResponseEntity<CarPart> getCarPartById(@PathVariable("id") Integer id) {
		ResponseEntity<CarPart> result = new ResponseEntity<CarPart>(HttpStatus.NO_CONTENT);
		Optional<CarPart> carPart = carPartService.getCarPartById(id);
		if(carPart.isPresent()) {
			result = new ResponseEntity<CarPart>(carPart.get(), HttpStatus.OK);	
		}
		return result;
	}
	
	@GetMapping("carParts")
	public ResponseEntity<List<CarPart>> getCarParts() {
		List<CarPart> carParts = carPartService.getAllCarParts();
		return new ResponseEntity<List<CarPart>>(carParts, HttpStatus.OK);	
	}
	
	@PostMapping("carParts")
	public ResponseEntity<Void> addCarPart(@RequestBody CarPart carPart, UriComponentsBuilder builder) {
                carPartService.addCarPart(carPart);
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/carParts/{id}").buildAndExpand(carPart.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
}

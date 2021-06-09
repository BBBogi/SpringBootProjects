package com.claim.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.claim.app.entity.CarPart;

public interface CarPartRepository extends CrudRepository<CarPart, Long> {

}

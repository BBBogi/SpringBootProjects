package com.claim.app.repository;


import org.springframework.data.repository.CrudRepository;

import com.claim.app.entity.Claim;

public interface ClaimRepository extends CrudRepository<Claim, Long> {
   
}

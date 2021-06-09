package com.claim.app.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.claim.app.dto.ClaimInfo;
import com.claim.app.entity.Claim;
import com.claim.app.service.ICarPartService;
import com.claim.app.service.IClaimService;
import com.claim.app.util.PairPartTotalCost;

@RestController
public class ClaimController {

	@Autowired
	private IClaimService claimService;
	@Autowired
	private ICarPartService carPartService;
	
	@GetMapping("claims/{id}")
	public ResponseEntity<Claim> getClaimById(@PathVariable("id") Integer id) {
		ResponseEntity<Claim> result = new ResponseEntity<Claim>(HttpStatus.NO_CONTENT);
		Optional<Claim> claim = claimService.getClaimById(id);
		if(claim.isPresent()) {
			result = new ResponseEntity<Claim>(claim.get(), HttpStatus.OK);	
		}
		return result;
	}
	
	@GetMapping("claims")
	public ResponseEntity<List<Claim>> getAllClaims() {
		List<Claim> claims = claimService.getAllClaims();
	    return new ResponseEntity<List<Claim>>(claims, HttpStatus.OK);		
	}
	
	@GetMapping("claims/details/{id}")
	public ResponseEntity<ClaimInfo> claimDetails(@PathVariable("id") Integer claimId) {
		ResponseEntity<ClaimInfo> result = new ResponseEntity<ClaimInfo>(HttpStatus.NO_CONTENT);
		Optional<Claim> retrievedClaimOpt = claimService.getClaimById(claimId);
		if(retrievedClaimOpt.isPresent()) {
			Claim retrievedClaim = retrievedClaimOpt.get();
			BigDecimal totalCost = retrievedClaim
					.getClaimItems()
					.stream()
					.map(ci-> ci.getMaterialCost().add(ci.getWageCost()))
					.reduce(BigDecimal.ZERO,BigDecimal::add);
			
			List<PairPartTotalCost> brokenCarParts = retrievedClaim
					.getClaimItems()
					.stream()
					.map(ci-> PairPartTotalCost.of(carPartService.getCarPartNameById(ci.getCarPartId()), ci.getMaterialCost().add(ci.getWageCost())))
					.collect(Collectors.toList());
			
			ClaimInfo response = new ClaimInfo(retrievedClaim.getId(),retrievedClaim.getContractNumber(),totalCost,brokenCarParts);
			result = new ResponseEntity<ClaimInfo>(response,HttpStatus.OK); 
		}
		return result;
	}
	
	@PostMapping("claims")
	public ResponseEntity<Void> addClaim(@RequestBody Claim claim, UriComponentsBuilder builder) {
                claimService.addClaim(claim);
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/claims/{id}").buildAndExpand(claim.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
}

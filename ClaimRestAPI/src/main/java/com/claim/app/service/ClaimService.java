package com.claim.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.app.entity.Claim;
import com.claim.app.repository.ClaimRepository;

@Service
public class ClaimService implements IClaimService {

	@Autowired
	private ClaimRepository claimRepository;
	
	@Override
	public List<Claim> getAllClaims() {
		List<Claim> list = new ArrayList<>();
		claimRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Optional<Claim> getClaimById(long claimId) {
		return claimRepository.findById(claimId);	
	}

	@Override
	public void addClaim(Claim claim) {
		claimRepository.save(claim);
	}

	@Override
	public void updateClaim(Claim claim) {
		claimRepository.save(claim);
	}

	@Override
	public void deleteClaim(long claimId) {
		Optional<Claim> claimToDelete = getClaimById(claimId);
		if(claimToDelete.isPresent()) {
			claimRepository.delete(claimToDelete.get());
		}
		
	}
}

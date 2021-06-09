package com.claim.app.service;

import java.util.List;
import java.util.Optional;

import com.claim.app.entity.Claim;

public interface IClaimService {

    List<Claim> getAllClaims();
    Optional<Claim> getClaimById(long claimId);
    void addClaim(Claim claim);
    void updateClaim(Claim claim);
    void deleteClaim(long claimId);
}

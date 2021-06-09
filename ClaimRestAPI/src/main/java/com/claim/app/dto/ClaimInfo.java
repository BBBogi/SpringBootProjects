package com.claim.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.claim.app.util.PairPartTotalCost;

public class ClaimInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long claimNumber;
	private Integer contractNumber;
	private BigDecimal totalCost;
	private List<PairPartTotalCost> brokenCarPartsWithTotalPrice;
	
	public ClaimInfo() {
	}
	
	public ClaimInfo(Long claimNumber, Integer contractNumber, BigDecimal totalCost, List<PairPartTotalCost> brokenCarPartsWithTotalPrice) {
		this.claimNumber = claimNumber;
		this.contractNumber = contractNumber;
		this.totalCost = totalCost;
		this.brokenCarPartsWithTotalPrice = brokenCarPartsWithTotalPrice;
	}
	
	public Long getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(Long claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Integer getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(Integer contractNumber) {
		this.contractNumber = contractNumber;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public List<PairPartTotalCost> getBrokenCarPartsWithTotalPrice() {
		return brokenCarPartsWithTotalPrice;
	}

	public void setBrokenCarPartsWithTotalPrice(List<PairPartTotalCost> brokenCarPartsWithTotalPrice) {
		this.brokenCarPartsWithTotalPrice = brokenCarPartsWithTotalPrice;
	}
	
	public String writeBrokenPartList() {
		StringBuilder sb = new StringBuilder();
		for(PairPartTotalCost partCost: this.brokenCarPartsWithTotalPrice) {
			sb.append(partCost.toString());      		
        }
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ClaimNumber: ");
		sb.append(this.getClaimNumber());
		sb.append(", ContractNumber: ");
		sb.append(this.getContractNumber());
		sb.append(", Total: ");
		sb.append(this.getTotalCost());
		sb.append(", brokenParts: ");
		sb.append(writeBrokenPartList());
		return    sb.toString();                  
	}
	
	
}
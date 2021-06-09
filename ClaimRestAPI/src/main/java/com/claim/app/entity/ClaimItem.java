package com.claim.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="CLAIM_ITEM")
public class ClaimItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column (name = "CLAIM_ITEM_ID")
	private Long id;
	@Column (name = "CAR_PART_ID", nullable = false)
	private Long carPartId;
	@Column (name = "MATERIAL_COST", nullable = false)
	private BigDecimal materialCost;
	@Column (name = "WAGE_COST", nullable = false)
	private BigDecimal wageCost;
	@ManyToOne
	@JsonBackReference
	@JoinColumn (name = "CLAIM_ID", nullable = false)
	private Claim claim;
	
	public ClaimItem() {
		
	}
	
	public ClaimItem(Long carPartId, BigDecimal materialCost, BigDecimal wageCost, Claim claim) {
		this.carPartId = carPartId;
		this.materialCost = materialCost;
		this.wageCost = wageCost;
		this.claim = claim;
	}

	public Long getId() {
		return id;
	}

	public Long getCarPartId() {
		return carPartId;
	}

	public void setCarPartId(Long carPartId) {
		this.carPartId = carPartId;
	}

	public BigDecimal getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(BigDecimal materialCost) {
		this.materialCost = materialCost;
	}

	public BigDecimal getWageCost() {
		return wageCost;
	}

	public void setWageCost(BigDecimal wageCost) {
		this.wageCost = wageCost;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ClaimItemId: ");
		sb.append(this.getId());
		sb.append(", CarPartId: ");
		sb.append(this.getCarPartId());
		sb.append(", MaterialCost: ");
		sb.append(this.getMaterialCost());
		sb.append(", WageCost: ");
		sb.append(this.getWageCost());
		sb.append(", ClaimId: ");
		sb.append(getClaim().getId());
		return    sb.toString();                  
	}
	
}

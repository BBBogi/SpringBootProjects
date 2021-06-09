package com.claim.app.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name="CLAIM")
public class Claim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column (name = "CLAIM_ID")
	private Long id;
	@Column (name = "CONTRACT_NUMBER", nullable = false)
	private Integer contractNumber;
	@Column (name = "COMPANY_NUMBER", nullable = false)
	private Integer companyNumber;
	@JsonManagedReference
	@OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ClaimItem> claimItems = Collections.emptyList();
	
	
	public Claim() {
		
	}
	
	public Claim(Integer contractNumber, Integer companyNumber) {
		this.contractNumber = contractNumber;
		this.companyNumber = companyNumber;
	}

	public Long getId() {
		return id;
	}

	public Integer getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(Integer contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Integer getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(Integer companyNumber) {
		this.companyNumber = companyNumber;
	}

	public List<ClaimItem> getClaimItems() {
		return Collections.unmodifiableList(claimItems);
	}

	public void setClaimItems(List<ClaimItem> claimItems) {
		this.claimItems = claimItems!=null? claimItems:Collections.emptyList();
	}

	public void addClaimItems(List<ClaimItem> claimItems) {
		claimItems.addAll(claimItems);
	}
	
	public String writeClaimItemsList() {
		StringBuilder sb = new StringBuilder();
		for(ClaimItem item: this.claimItems) {
			sb.append(item.toString());      		
        }
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ClaimId: ");
		sb.append(this.getId());
		sb.append(", ContractNumber: ");
		sb.append(this.getContractNumber());
		sb.append(", CompanyNumber: ");
		sb.append(this.getCompanyNumber());
		sb.append(", Claim Items: ");
		sb.append(writeClaimItemsList());
		return    sb.toString();                  
	}

}

package com.claim.app.util;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

public class PairPartTotalCost implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String carPartName;
	private BigDecimal cost;

	 public PairPartTotalCost() {
	 
		
	 }
	 
	 public static PairPartTotalCost of(String carPartName, BigDecimal cost) {
		 return new PairPartTotalCost(carPartName, cost);
	 }
	
	public PairPartTotalCost(String carPartName, BigDecimal cost) {
		this.carPartName = carPartName;
		this.cost = cost;
	}

	public String getCarPartName() {
		return carPartName;
	}

	public BigDecimal getCost() {
		return cost;
	}

	@Override
	public boolean equals(@Nullable Object o) {

		if (this == o) {
			return true;
		}

		if (!(o instanceof PairPartTotalCost)) {
			return false;
		}

		PairPartTotalCost pair = (PairPartTotalCost) o;

		if (!ObjectUtils.nullSafeEquals(carPartName, pair.carPartName)) {
			return false;
		}

		return ObjectUtils.nullSafeEquals(cost, pair.cost);
	}

	@Override
	public int hashCode() {
		int result = ObjectUtils.nullSafeHashCode(carPartName);
		result = 31 * result + ObjectUtils.nullSafeHashCode(cost);
		return result;
	}
	
	@Override
	public String toString() {
		return "carPartName: " + getCarPartName() + ", cost: " + getCost();
	}
}

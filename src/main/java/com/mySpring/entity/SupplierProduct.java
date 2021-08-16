package com.mySpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplierproduct_tbl")
@Data 

public class SupplierProduct {
	@EmbeddedId
    private SupplierProductPKID supplierProductPKID;
	private double quantity;
}

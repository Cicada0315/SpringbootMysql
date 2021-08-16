package com.mySpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mySpring.entity.SupplierProduct;
import com.mySpring.entity.SupplierProductPKID;

public interface SupplierProductRepository extends JpaRepository<SupplierProduct, SupplierProductPKID>{
	
}
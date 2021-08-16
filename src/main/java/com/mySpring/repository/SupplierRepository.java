package com.mySpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mySpring.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer>{
	Supplier findByName(String name);
}
package com.mySpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mySpring.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	Product findByName(String name);
}
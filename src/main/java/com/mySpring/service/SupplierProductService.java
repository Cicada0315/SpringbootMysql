package com.mySpring.service;

import com.mySpring.entity.SupplierProduct;
import com.mySpring.repository.SupplierProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierProductService {
    @Autowired
    private SupplierProductRepository repository;

    public SupplierProduct saveSupplierProduct(SupplierProduct supplierproduct) {
        return repository.save(supplierproduct);
    }

    public List<SupplierProduct> saveSupplierProducts(List<SupplierProduct> supplierproducts) {
        return repository.saveAll(supplierproducts);
    }

    public List<SupplierProduct> getSupplierProducts() {
        return repository.findAll();
    }
   
}

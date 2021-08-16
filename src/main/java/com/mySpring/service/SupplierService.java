package com.mySpring.service;

import com.mySpring.entity.Supplier;
import com.mySpring.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository repository;

    public Supplier saveSupplier(Supplier supplier) {
        return repository.save(supplier);
    }

    public List<Supplier> saveSuppliers(List<Supplier> suppliers) {
        return repository.saveAll(suppliers);
    }

    public List<Supplier> getSuppliers() {
        return repository.findAll();
    }

    public Supplier getSupplierById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Supplier getSupplierByName(String name) {
        return repository.findByName(name);
    }

    public String deleteSupplier(int id) {
        repository.deleteById(id);
        return "Supplier removed !! " + id;
    }

    public Supplier updateSupplier(Supplier supplier) {
    	Supplier existingSupplier = repository.findById(supplier.getId()).orElse(null);
        existingSupplier.setName(supplier.getName());
        return repository.save(existingSupplier);
    }
}

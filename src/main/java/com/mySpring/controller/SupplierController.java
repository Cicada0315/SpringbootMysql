package com.mySpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mySpring.entity.Supplier;
import com.mySpring.service.SupplierService;

@RestController
public class SupplierController {
	@Autowired
    private SupplierService service;

    @PostMapping("/addSupplier")
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return service.saveSupplier(supplier);
    }

    @PostMapping("/addSuppliers")
    public List<Supplier> addSuppliers(@RequestBody List<Supplier> suppliers) {
        return service.saveSuppliers(suppliers);
    }

    @GetMapping("/suppliers")
    public List<Supplier> findAllSuppliers() {
        return service.getSuppliers();
    }

    @GetMapping("/supplierById/{id}")
    public Supplier findSupplierById(@PathVariable int id) {
        return service.getSupplierById(id);
    }

    @GetMapping("/supplier/{name}")
    public Supplier findSupplierByName(@PathVariable String name) {
        return service.getSupplierByName(name);
    }

    @PutMapping("/updateSupplier")
    public Supplier updateSupplier(@RequestBody Supplier supplier) {
        return service.updateSupplier(supplier);
    }

    @DeleteMapping("deleteSupplier/{id}")
    public String deleteSupplier(@PathVariable int id) {
        return service.deleteSupplier(id);
    }
}
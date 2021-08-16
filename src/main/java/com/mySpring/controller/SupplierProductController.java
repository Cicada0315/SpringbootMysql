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


import com.mySpring.entity.SupplierProduct;
import com.mySpring.service.SupplierProductService;

@RestController
public class SupplierProductController {
	@Autowired
    private SupplierProductService service;

    @PostMapping("/addSupplierProduct")
    public SupplierProduct addSupplierProduct(@RequestBody SupplierProduct supplierproduct) {
        return service.saveSupplierProduct(supplierproduct);
    }

    @PostMapping("/addSupplierProducts")
    public List<SupplierProduct> addSupplierProducts(@RequestBody List<SupplierProduct> supplierproducts) {
        return service.saveSupplierProducts(supplierproducts);
    }

    @GetMapping("/supplierProduct")
    public List<SupplierProduct> findAllSupplierProducts() {
        return service.getSupplierProducts();
    }
}
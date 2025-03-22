package com.example.pf_ecsite.repository;

import com.example.pf_ecsite.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

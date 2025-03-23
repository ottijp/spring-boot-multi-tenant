package com.example.demo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.jpa.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

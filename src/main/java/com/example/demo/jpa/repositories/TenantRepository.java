package com.example.demo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.jpa.entities.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
}

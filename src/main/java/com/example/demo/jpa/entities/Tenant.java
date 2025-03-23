package com.example.demo.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tenants")
public class Tenant {

  @Id
  private String tenantName;

  @Column(nullable = false)
  private String schemaName;

}

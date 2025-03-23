package com.example.demo;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.jpa.entities.Product;
import com.example.demo.jpa.repositories.TenantRepository;
import com.example.demo.jpa.repositories.ProductRepository;

@Service
@Slf4j
class ProductService {

  @Autowired
  private TenantRepository tenantRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private TenantIdentifierResolver tenantIdentifierResolver;

  public List<Product> loadProducts(String tenantId) {
    log.info("loadProducts: tenantId={}", tenantId);

    // get tenant information
    log.info("start to get tenant information");
    tenantIdentifierResolver.setCurrentTenant("management");
    var tenants = tenantRepository.findById(tenantId);
    log.info("tenants: {}", tenants);

    // get product from tenant schema
    log.info("start to get product from tenant schema");
    tenantIdentifierResolver.setCurrentTenant(tenants.get().getSchemaName());
    var products = productRepository.findAll();
    log.info("products: {}", products);

    return products;
  }

  public String loadSchemaName(String tenantId) {
    log.info("loadSchemaName: tenantId={}", tenantId);

    // get tenant information
    log.info("start to get tenant information");
    tenantIdentifierResolver.setCurrentTenant("management");
    var tenants = tenantRepository.findById(tenantId);
    log.info("tenants: {}", tenants);

    return tenants.get().getSchemaName();
  }

  @Transactional
  public List<Product> loadTenantProducts() {
    log.info("loadTenantProducts");
    var products = productRepository.findAll();
    log.info("product: {}", products);
    return products;
  }

}

package com.example.demo;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.entities.Product;

@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication {

  @Autowired
  private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @GetMapping("/products")
  public List<Product> products(@RequestParam("tenantId") String tenantId) {
    log.info("/products: tenantId={}", tenantId);
    var products = productService.loadProducts(tenantId);
    return products;
  }

  @GetMapping("/products_transactional")
  public List<Product> productsTransactional(@RequestParam("tenantId") String tenantId) {
    log.info("/products_transactional: tenantId={}", tenantId);
    var schemaName = productService.loadSchemaName(tenantId);
    TenantContext.setCurrentTenant(schemaName);
    var products = productService.loadTenantProducts();
    return products;
  }

}

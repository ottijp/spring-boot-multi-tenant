package com.example.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {
  private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

  public static String getCurrentTenant() {
    return currentTenant.get();
  }

  public static void setCurrentTenant(String tenantId) {
    log.info("setCurrentTenant: tenantId={}", tenantId);
    currentTenant.set(tenantId);
  }

  public static void clear() {
    currentTenant.set(null);
  }
}

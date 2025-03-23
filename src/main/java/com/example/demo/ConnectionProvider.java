package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class ConnectionProvider implements MultiTenantConnectionProvider<String>, HibernatePropertiesCustomizer {

  @Autowired
  DataSource dataSource;

  @Override
  public Connection getAnyConnection() throws SQLException {
    return getConnection("PUBLIC");
  }

  @Override
  public void releaseAnyConnection(Connection connection) throws SQLException {
    connection.close();
  }

  @Override
  public void customize(Map<String, Object> hibernateProperties) {
    hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
  }

  @Override
  public boolean isUnwrappableAs(Class aClass) {
    return false;
  }

  @Override
  public <T> T unwrap(Class<T> aClass) {
    return null;
  }

  @Override
  public Connection getConnection(String tenantIdentifier) throws SQLException {
    Connection connection = dataSource.getConnection();
    log.info("getConnection: tenantId={}, connection={}", tenantIdentifier, connection.toString());
    connection.setSchema(tenantIdentifier);
    return connection;
  }

  @Override
  public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
    log.info("releaseConnection: tenantId={}, connection={}", tenantIdentifier, connection.toString());
    connection.setSchema("PUBLIC");
    connection.close();
  }

  @Override
  public boolean supportsAggressiveRelease() {
    return false;
  }
}

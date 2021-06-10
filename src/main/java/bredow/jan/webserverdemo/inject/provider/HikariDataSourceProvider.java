package bredow.jan.webserverdemo.inject.provider;

import bredow.jan.webserverdemo.conf.Configuration;
import bredow.jan.webserverdemo.conf.db.DatabaseConfiguration;
import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Singleton
@Component
public final class HikariDataSourceProvider {
  private final Configuration configuration;
  private HikariDataSource dataSource = null;

  @Autowired
  private HikariDataSourceProvider(ConfigurationProvider configurationProvider) {
    this.configuration = configurationProvider.get();
  }

  public HikariDataSource get() {

    if (dataSource != null) {
      return dataSource;
    }

    var config = createConfig();

    dataSource = new HikariDataSource(config);
    dataSource.setConnectionTimeout(30000);
    dataSource.setLeakDetectionThreshold(60 * 1000);

    return dataSource;
  }

  private HikariConfig createConfig() {

    var hikariConfig = new HikariConfig();

    var poolConfig = configuration.databasePoolConfiguration();
    var databaseConfiguration = configuration.databaseConfiguration();
    var databaseCredentials = databaseConfiguration.databaseCredentials();

    hikariConfig.setJdbcUrl(jdbcUrlFrom(databaseConfiguration));
    hikariConfig.setUsername(databaseCredentials.username());
    hikariConfig.setPassword(databaseCredentials.password());

    hikariConfig.setDriverClassName("org.postgresql.Driver");
    hikariConfig.setMaximumPoolSize(poolConfig.maximumPoolSize());
    hikariConfig.setMinimumIdle(poolConfig.minimumIdle());
    hikariConfig.addDataSourceProperty("cachePrepStmts", poolConfig.cachePrepStmts());
    hikariConfig.addDataSourceProperty("prepStmtCacheSize", poolConfig.prepStmtCacheSize());
    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", poolConfig.prepStmtCacheSqlLimit());
    hikariConfig.addDataSourceProperty("useServerPrepStmts", poolConfig.useServerPrepStmts());
    hikariConfig.addDataSourceProperty("useLocalSessionState", poolConfig.useLocalSessionState());
    hikariConfig.addDataSourceProperty("rewriteBatchedStatements", poolConfig.rewriteBatchedStatements());
    hikariConfig.addDataSourceProperty("cacheResultSetMetadata", poolConfig.cacheResultSetMetadata());
    hikariConfig.addDataSourceProperty("cacheServerConfiguration", poolConfig.cacheServerConfiguration());
    hikariConfig.addDataSourceProperty("elideSetAutoCommits", poolConfig.elideSetAutoCommits());
    hikariConfig.addDataSourceProperty("maintainTimeStats", poolConfig.maintainTimeStats());
    hikariConfig.addDataSourceProperty("ApplicationName", poolConfig.applicationName());

    return hikariConfig;
  }

  private String jdbcUrlFrom(DatabaseConfiguration databaseConfiguration) {
    return "jdbc:postgresql://"
        + databaseConfiguration.databaseHost()
        + ":"
        + databaseConfiguration.databasePort()
        + "/"
        + databaseConfiguration.databaseName();
  }
}

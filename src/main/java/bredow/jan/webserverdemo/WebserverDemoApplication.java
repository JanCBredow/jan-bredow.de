package bredow.jan.webserverdemo;

import bredow.jan.webserverdemo.conf.db.DatabaseConfiguration;
import bredow.jan.webserverdemo.conf.db.DatabasePoolConfiguration;
import bredow.jan.webserverdemo.inject.provider.ConfigurationProvider;
import bredow.jan.webserverdemo.inject.provider.HikariDataSourceProvider;
import bredow.jan.webserverdemo.log.ConsoleLogger;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("SpellCheckingInspection")
@SpringBootApplication
public class WebserverDemoApplication {
  private static final ConsoleLogger LOG = ConsoleLogger.of(WebserverDemoApplication.class);


  public static void main(String[] args) {
    SpringApplication.run(WebserverDemoApplication.class, args);
  }

  @Component
  public static class Server {

    @Autowired
    public void prepare(
        ConfigurationProvider configurationProvider,
        HikariDataSourceProvider hikariDataSourceProvider) {

      // Load Configuration File or creates a new one
      var configuration = configurationProvider.get();
      var poolConfiguration = configuration.databasePoolConfiguration();
      var databaseConfiguration = configuration.databaseConfiguration();

      LOG.space()
          .log("Booting up using following Configuration-Entrys (Passwords hidden)")
          .space(1)
          .logSpaced("Found Database Configuration using following Entries", 2)
          .invokeLogger(printConfigurationProperties(databaseConfiguration))
          .logSpaced("", 4)
          .logSpaced("Connecting to the Database using the following Database Settings", 2)
          .invokeLogger(printPoolProperties(poolConfiguration));

      var pool = hikariDataSourceProvider.get();

      if (!isConnected(pool)) {
        LOG.error("Unable to create database connection!").error("fallback to non-db mode");
      } else {
        LOG.info("Successfully connected to the Database.");
      }

      // Give User ability to execute Commands or enable Debug

      Thread terminal = new Thread(this::enableScanner, "JCB_TERMINAL");
      terminal.start();

    }

    private void enableScanner() {
      var scanner = new Scanner(System.in);
    }

    private boolean isConnected(HikariDataSource pool) {
      try (var connection = pool.getConnection()) {
        return connection.isValid(5);
      } catch (SQLException ignored) {
        return false;
      }
    }

    private ConsoleLogger printConfigurationProperties(
        DatabaseConfiguration databaseConfiguration) {
      return LOG
          .logSpaced("Hostname:  \"" + databaseConfiguration.databaseHost() + "\"", 4)
          .logSpaced("Port:      \"" + databaseConfiguration.databasePort() + "\"", 4)
          .logSpaced("Database:  \"" + databaseConfiguration.databaseName() + "\"", 4)
          .invokeLogger(printDatabaseUserCredentials(databaseConfiguration));
    }

    private ConsoleLogger printDatabaseUserCredentials(
        DatabaseConfiguration databaseConfiguration) {
      var databaseCredentials = databaseConfiguration.databaseCredentials();
      return LOG
          .logSpaced("Username:  \"" + databaseCredentials.username() + "\"", 4)
          .logSpaced("Password:  \"" + "*".repeat(databaseCredentials.password().length()), 4);
    }

    private ConsoleLogger printPoolProperties(DatabasePoolConfiguration poolConfiguration) {
      return LOG
          .logSpaced("Application-Name:                   \"" + poolConfiguration.applicationName() + "\"", 4)
          .logSpaced("Cache Server Configuration:         \"" + poolConfiguration.cacheServerConfiguration() + "\"", 4)
          .logSpaced("Cache Prepared Statements:          \"" + poolConfiguration.cachePrepStmts() + "\"", 4)
          .logSpaced("Cache RestultSetMetadata:           \"" + poolConfiguration.cacheResultSetMetadata() + "\"", 4)
          .logSpaced("Cache Size for Statements:          \"" + poolConfiguration.prepStmtCacheSize() + "\"", 4)
          .logSpaced("Cache Size for Prepared Statements: \"" + poolConfiguration.prepStmtCacheSqlLimit() + "\"", 4)
          .space()
          .logSpaced("Maximum Pool-Size:                  \"" + poolConfiguration.maximumPoolSize() + "\"", 4)
          .logSpaced("Use Server Prepared Statements?     \"" + poolConfiguration.useServerPrepStmts() + "\"", 4)
          .logSpaced("Use Local Session State?            \"" + poolConfiguration.useLocalSessionState() + "\"", 4)
          .logSpaced("Rewrite Batch Statements?           \"" + poolConfiguration.rewriteBatchedStatements() + "\"", 4)
          .logSpaced("Elide Set Auto-Commits?             \"" + poolConfiguration.elideSetAutoCommits() + "\"", 4)
          .logSpaced("Maintain Time-Stats?                \"" + poolConfiguration.maintainTimeStats() + "\"", 4);
    }
  }
}

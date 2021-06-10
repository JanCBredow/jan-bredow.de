package bredow.jan.webserverdemo.conf;

import bredow.jan.webserverdemo.conf.db.DatabaseConfiguration;
import bredow.jan.webserverdemo.conf.db.DatabasePoolConfiguration;

public final class Configuration {

  private final DatabaseConfiguration databaseConfiguration;
  private final DatabasePoolConfiguration databasePoolConfiguration;

  private Configuration(
      DatabaseConfiguration databaseConfiguration,
      DatabasePoolConfiguration databasePoolConfiguration) {
    this.databaseConfiguration = databaseConfiguration;
    this.databasePoolConfiguration = databasePoolConfiguration;
  }

  public static Configuration createWith(
      DatabaseConfiguration initialDatabaseConfiguration,
      DatabasePoolConfiguration databasePoolConfiguration) {
    return new Configuration(initialDatabaseConfiguration, databasePoolConfiguration);
  }

  public DatabaseConfiguration databaseConfiguration() {
    return databaseConfiguration;
  }

  public DatabasePoolConfiguration databasePoolConfiguration() {
    return databasePoolConfiguration;
  }
}

package bredow.jan.webserverdemo.conf;

import bredow.jan.webserverdemo.conf.db.DatabaseConfiguration;
import bredow.jan.webserverdemo.conf.db.DatabasePoolConfiguration;

public final class Configuration {
  private final DatabaseConfiguration databaseConfiguration;
  private final DatabasePoolConfiguration databasePoolConfiguration;

  private Configuration(DatabaseConfiguration databaseConfiguration, DatabasePoolConfiguration databasePoolConfiguration) {
    this.databaseConfiguration = databaseConfiguration;
    this.databasePoolConfiguration = databasePoolConfiguration;
  }

  public static Configuration createWith(DatabaseConfiguration databaseConfiguration, DatabasePoolConfiguration databasePoolConfiguration) {
    return new Configuration(databaseConfiguration, databasePoolConfiguration);
  }


  public DatabaseConfiguration databaseConfiguration() {
    return databaseConfiguration;
  }

  public DatabasePoolConfiguration databasePoolConfiguration() {
    return databasePoolConfiguration;
  }
}
